package sun.study.note.module.pro.processor;


import sun.study.note.common.result.Result;
import sun.study.note.module.pro.context.DemoContext;
import sun.study.note.module.pro.context.ProResult;

import java.util.Objects;

/**
 * @author sunzhen
 * @data 2022/4/26 17:21
 */
public class OrderProcessor extends AbstractProcessor<DemoContext, Result<ProResult>> {

    @Override
    public void process(DemoContext context, Result<ProResult> result) {
        String orderId = context.getOrderId();
        ProResult proResult = new ProResult();
        if (Objects.nonNull(orderId)) {
            proResult.setOrderSucceed(true);
            System.out.println("处理订单：" + orderId);
        } else {
            proResult.setOrderSucceed(false);
            System.out.println("订单号空");
        }
        result.setResult(proResult);
    }

    @Override
    public boolean preCheck(DemoContext context, Result<ProResult> result) {
        if (Objects.isNull(context)) {
            throw new RuntimeException("参数为空！");
        }
        return true;
    }

}
