package sun.study.note;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author sunzhen <sunzhen03@kuaishou.com>
 * Created on 2021-06-29
 */
@SpringBootApplication
@MapperScan("sun.study.note.dao")
@EnableAspectJAutoProxy
public class MybatisPlusApp {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApp.class, args);
    }


}
