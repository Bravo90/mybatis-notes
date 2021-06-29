package sun.study.note.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import sun.study.note.domian.Student;
import sun.study.note.domian.StudentCourse;
import sun.study.note.domian.dto.StudentCourseDTO;

/**
 * <p>
 * 学生-课程关系表 Mapper 接口
 * </p>
 *
 * @author sunzhen03<sunzhen03 @ kuaishou.com>
 * @since 2021-06-29
 */
public interface StudentCourseMapper extends BaseMapper<StudentCourse> {

    @Select("SELECT t1.id,t1.NAME,t2.course_id FROM ts_student t1, ts_student_course t2 WHERE t1.id = t2.student_id AND t2.course_id = #{courseId}")
    IPage<StudentCourseDTO> selectPageDTO(Page<?> page, @Param("courseId") Integer courseId);
}
