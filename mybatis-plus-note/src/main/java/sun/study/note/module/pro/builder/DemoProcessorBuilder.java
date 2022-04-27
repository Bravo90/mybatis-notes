package sun.study.note.module.pro.builder;

import org.springframework.stereotype.Component;
import sun.study.note.common.result.Result;
import sun.study.note.module.pro.AbstractProcessorBuilder;
import sun.study.note.module.pro.context.DemoContext;
import sun.study.note.module.pro.context.ProResult;
import sun.study.note.module.pro.processor.OrderProcessor;
import sun.study.note.module.pro.processor.PayProcessor;
import sun.study.note.module.pro.processor.PostProcessor;

/**
 * @author sunzhen
 * @data 2022/4/26 17:25
 */
@Component
public class DemoProcessorBuilder extends AbstractProcessorBuilder<DemoContext, Result<ProResult>> {

    @Override
    public void initProcessor() {
        addLast(new OrderProcessor());
        addLast(new PayProcessor());
        addLast(new PostProcessor());
    }
}
