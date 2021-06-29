package sun.study.note.service.impl;

import sun.study.note.domian.Student;
import sun.study.note.dao.StudentMapper;
import sun.study.note.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sunzhen03<sunzhen03@kuaishou.com>
 * @since 2021-06-29
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
