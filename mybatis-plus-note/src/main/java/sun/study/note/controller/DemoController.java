package sun.study.note.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.study.note.common.result.Result;
import sun.study.note.module.pro.builder.DemoProcessorBuilder;
import sun.study.note.module.pro.context.DemoContext;
import sun.study.note.module.pro.context.ProResult;

/**
 * @author sunzhen
 * @data 2022/4/26 17:30
 */
@RestController
@RequestMapping("/demo")
@Api(value = "DemoController")
public class DemoController {

    @Autowired
    private DemoProcessorBuilder demoProcessorBuilder;

    @ApiOperation("Demo Processor")
    @GetMapping("/pro")
    public Result<Object> pro(String orderId) {
        DemoContext demoContext = new DemoContext();
        demoContext.setOrderId(orderId);

        Result<ProResult> result = new Result<>();
        demoProcessorBuilder.build().invoke(demoContext, result);
        return Result.ok(result);
    }
}
