package sun.study.note.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * @author sunzhen <sunzhen03@kuaishou.com>
 * Created on 2021-06-25
 */
@Component
@Slf4j
public class UserFilter extends GenericFilter implements Ordered {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String userId = UUID.randomUUID().toString();


        if (Objects.nonNull(userId) && !"".equals(userId)) {
            log.error(userId);
            UserUtil.putUser(userId);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
            log.error("用户尚未登陆");
           // throw new BusinessRuntimeException("用户尚未登陆");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
