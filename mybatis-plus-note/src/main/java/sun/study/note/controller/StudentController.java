package sun.study.note.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import sun.study.note.common.result.Result;

import sun.study.note.domian.Student;
import sun.study.note.domian.dto.ProjectDeliveryDTO;
import sun.study.note.domian.dto.StudentCourseDTO;
import sun.study.note.domian.dto.StudentDTO;
import sun.study.note.service.StudentCourseService;
import sun.study.note.service.StudentService;

import java.util.Collections;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sunzhen03<sunzhen03 @ kuaishou.com>
 * @since 2021-06-29
 */
@RestController
@RequestMapping("/student")
@Api(value = "StudentController")
public class StudentController {

    @Autowired
    private StudentCourseService studentCourseService;

    @Autowired
    private StudentService studentService;

    @ApiOperation("保存信息")
    @GetMapping("/getStudent")
    public Result<Student> getStudent(int id) {

        Student student = studentService.getById(id);
        return Result.ok(student);
    }

    @ApiOperation("保存信息")
    @PostMapping("/save")
    public Result<ProjectDeliveryDTO> save(@RequestBody  ProjectDeliveryDTO projectDeliveryDTO) {
        return Result.ok(projectDeliveryDTO);
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public Result<IPage<StudentCourseDTO>> page() {
        IPage<StudentCourseDTO> studentCourseDTOIPage = studentCourseService.selectStudentCoursePage(new Page<>(1, 2), 1);
        return Result.ok(studentCourseDTOIPage);
    }

    @ApiOperation("批量插入")
    @GetMapping("/batch/save")
    public Result batchSave(@RequestParam int batchSize) {
        studentService.batchSave(batchSize);

        return Result.ok();
    }

}
