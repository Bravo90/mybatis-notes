package sun.study.note.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import sun.study.note.domian.Student;
import sun.study.note.dao.StudentMapper;
import sun.study.note.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sunzhen03<sunzhen03 @ kuaishou.com>
 * @since 2021-06-29
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    private ExecutorService service = new ThreadPoolExecutor(1, 1, 1000, TimeUnit.SECONDS, new LinkedBlockingQueue<>(2), new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            throw new RuntimeException("正在创建的二维码批次超过最大值，最大值为20");
        }
    });

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Override
    public void batchSave(int batchSize) {
        service.submit(new SaveTask(batchSize));

//        Future<Boolean> future = service.submit(new SaveCallBack(batchSize));
//        try {
//            Boolean aBoolean = future.get();
//            if (aBoolean) {
//                //
//                System.out.println("批量创建成功");
//            } else {
//                System.out.println("批量创建失败");
//            }
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
    }


    class SaveCallBack implements Callable<Boolean> {

        private int batchSize;

        public SaveCallBack(int batchSize) {
            this.batchSize = batchSize;
        }

        @Override
        public Boolean call() throws Exception {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            TransactionStatus status = platformTransactionManager.getTransaction(def);
            try {
                System.out.println("----save task begin ---");
                System.out.println(service.toString());
                String uuid = UUID.randomUUID().toString();
                List<Student> list = new ArrayList<>();
                for (int i = 0; i < batchSize; i++) {
                    Student student = new Student();
                    student.setName(uuid);
                    list.add(student);
                }
                long t1 = System.currentTimeMillis();
                saveBatch(list);
                int a = 1222 / (batchSize % 10);

                Student student = new Student();
                student.setName(uuid);
                save(student);
                System.out.println(System.currentTimeMillis() - t1);
                platformTransactionManager.commit(status);
            } catch (Exception e) {
                platformTransactionManager.rollback(status);
                log.error("保存异常：", e);
                return false;
            }
            return true;
        }
    }


    class SaveTask implements Runnable {

        private int batchSize;

        public SaveTask(int batchSize) {
            this.batchSize = batchSize;
        }

        @Override
        public void run() {

            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            TransactionStatus status = platformTransactionManager.getTransaction(def);
            try {
                System.out.println("----save task begin ---");
                System.out.println(service.toString());
                String uuid = UUID.randomUUID().toString();
                List<Student> list = new ArrayList<>();
                for (int i = 0; i < batchSize; i++) {
                    Student student = new Student();
                    student.setName(uuid);
                    list.add(student);
                }
                long t1 = System.currentTimeMillis();
                System.out.println("==================");
                System.out.println(list);
                System.out.println("==================");
                saveBatch(list);
                System.out.println("==================");
                System.out.println(list);
                System.out.println("==================");
                System.out.println(System.currentTimeMillis() - t1);
                platformTransactionManager.commit(status);
                log.warn("批量生成完毕");
            } catch (Exception e) {
                platformTransactionManager.rollback(status);
                log.error("保存异常：", e);
            }
        }
    }
}
