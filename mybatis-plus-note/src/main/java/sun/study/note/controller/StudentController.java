package sun.study.note.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import sun.study.note.common.result.Result;

import sun.study.note.domian.dto.StudentCourseDTO;
import sun.study.note.domian.dto.StudentDTO;
import sun.study.note.service.StudentCourseService;

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

    @ApiOperation("保存信息")
    @PostMapping("/save")
    public Result<StudentDTO> save(@RequestBody @Validated StudentDTO studentDTO) {
        return Result.ok(studentDTO);
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public Result<IPage<StudentCourseDTO>> page() {
        IPage<StudentCourseDTO> studentCourseDTOIPage = studentCourseService.selectStudentCoursePage(new Page<>(1, 2), 1);
        return Result.ok(studentCourseDTOIPage);
    }
}
