package sun.study.note.spring.service.prod;

import sun.study.note.spring.service.DemoService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author sunzhen <sunzhen03@kuaishou.com>
 * Created on 2021-06-18
 */
@Service("demoService")
@Profile("prod")
public class DemoServiceImpl implements DemoService {
    @Override
    public void service() {
        System.out.println("prod");
    }
}
