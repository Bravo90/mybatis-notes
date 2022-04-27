package sun.study.note.module.pro.processor;

import sun.study.note.common.result.Result;
import sun.study.note.module.pro.context.DemoContext;
import sun.study.note.module.pro.context.ProResult;

import java.util.Objects;

/**
 * @author sunzhen
 * @data 2022/4/26 17:21
 */
public class PostProcessor extends AbstractProcessor<DemoContext, Result<ProResult>> {

    @Override
    public void process(DemoContext context, Result<ProResult> result) {
        System.out.println("发送快递...");
        result.getResult().setPostSucceed(true);
    }

    @Override
    public boolean preCheck(DemoContext context, Result<ProResult> result) {
        if (Objects.nonNull(result) && result.getResult().isPaySucceed()) {
            return true;
        }
        return false;
    }
}