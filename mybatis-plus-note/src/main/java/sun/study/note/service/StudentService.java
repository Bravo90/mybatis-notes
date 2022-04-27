package sun.study.note.service;

import sun.study.note.domian.Student;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.concurrent.ExecutionException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sunzhen03<sunzhen03@kuaishou.com>
 * @since 2021-06-29
 */
public interface StudentService extends IService<Student> {

    void batchSave(int batchSize) ;
}
