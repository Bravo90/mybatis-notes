package sun.study.note.service.impl;

import sun.study.note.domian.StudentCourse;
import sun.study.note.dao.StudentCourseMapper;
import sun.study.note.service.StudentCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生-课程关系表 服务实现类
 * </p>
 *
 * @author sunzhen03<sunzhen03@kuaishou.com>
 * @since 2021-06-29
 */
@Service
public class StudentCourseServiceImpl extends ServiceImpl<StudentCourseMapper, StudentCourse> implements StudentCourseService {

}
