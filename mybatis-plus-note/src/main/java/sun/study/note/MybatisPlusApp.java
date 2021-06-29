package sun.study.note;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.study.note.common.result.Result;
import sun.study.note.domian.dto.StudentCourseDTO;
import sun.study.note.service.StudentCourseService;

/**
 * @author sunzhen <sunzhen03@kuaishou.com>
 * Created on 2021-06-29
 */
@SpringBootApplication
@MapperScan("sun.study.note.dao")
@EnableAspectJAutoProxy
@RestController
public class MybatisPlusApp {

    @Autowired
    private StudentCourseService studentCourseService;

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApp.class, args);
    }

    @GetMapping("/page")
    public Result<IPage<StudentCourseDTO>> page() {
        IPage<StudentCourseDTO> studentCourseDTOIPage = studentCourseService.selectStudentCoursePage(new Page<>(1, 2), 1);
        return Result.ok(studentCourseDTOIPage);
    }
}
