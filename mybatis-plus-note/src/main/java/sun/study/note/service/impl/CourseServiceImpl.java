package sun.study.note.service.impl;

import sun.study.note.domian.Course;
import sun.study.note.dao.CourseMapper;
import sun.study.note.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程表 服务实现类
 * </p>
 *
 * @author sunzhen03<sunzhen03@kuaishou.com>
 * @since 2021-06-29
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

}
