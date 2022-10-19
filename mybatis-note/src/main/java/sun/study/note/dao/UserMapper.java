package sun.study.note.dao;

import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import sun.study.note.model.User;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author sunzhen03 <sunzhen03@inspur.com>
 * @since 2022-10-18
 */
public interface UserMapper {
    List<User> list(@Param("name") String name, @Param("age") Integer age);
}
