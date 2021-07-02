package sun.study.note.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import sun.study.note.domian.StudentCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import sun.study.note.domian.dto.StudentCourseDTO;

/**
 * <p>
 * 学生-课程关系表 服务类
 * </p>
 *
 * @author sunzhen03<sunzhen03 @ kuaishou.com>
 * @since 2021-06-29
 */
public interface StudentCourseService extends IService<StudentCourse> {

    IPage<StudentCourseDTO> selectStudentCoursePage(Page<StudentCourseDTO> page, Integer courseId);
}
