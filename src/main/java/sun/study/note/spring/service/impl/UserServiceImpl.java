package sun.study.note.spring.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import sun.study.note.mapper.UserMapper;
import sun.study.note.model.User;
import sun.study.note.spring.service.UserService;

/**
 * @author sunzhen <sunzhen03@kuaishou.com>
 * Created on 2021-06-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
