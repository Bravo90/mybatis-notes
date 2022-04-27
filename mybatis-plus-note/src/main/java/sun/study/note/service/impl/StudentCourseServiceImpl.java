package sun.study.note.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import sun.study.note.domian.StudentCourse;
import sun.study.note.dao.StudentCourseMapper;
import sun.study.note.domian.dto.StudentCourseDTO;
import sun.study.note.service.StudentCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生-课程关系表 服务实现类
 * </p>
 *
 * @author sunzhen03<sunzhen03 @ kuaishou.com>
 * @since 2021-06-29
 */
@Service
public class StudentCourseServiceImpl extends ServiceImpl<StudentCourseMapper, StudentCourse> implements StudentCourseService {

    @Autowired
    private StudentCourseMapper studentCourseMapper;

    @Override
    public IPage<StudentCourseDTO> selectStudentCoursePage(Page<StudentCourseDTO> page, Integer courseId) {
        return studentCourseMapper.selectPageDTO(page, courseId);
    }

    @Override
    public void batchSave() {
    }
}
