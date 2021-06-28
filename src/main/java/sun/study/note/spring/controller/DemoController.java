package sun.study.note.spring.controller;

import org.springframework.web.bind.annotation.*;
import sun.study.note.spring.anno.TestAnno;
import sun.study.note.mapper.UserMapper;
import sun.study.note.model.User;
import sun.study.note.spring.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Resource;
import java.lang.management.GarbageCollectorMXBean;
import java.util.Date;

/**
 * @author sunzhen <sunzhen03@kuaishou.com>
 * Created on 2021-06-16
 */
@RequestMapping("demo")
@RestController
@Api(value = "demo-api")
public class DemoController {

    @Resource
    private DemoService demoService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("config")
    @ApiOperation("获取user信息")
    @TestAnno(paramClass = User.class, type = 1)
    public String getMsg(@RequestBody User user) {
        return user.toString();
    }

    @GetMapping("getDate")
    @TestAnno(paramClass = String.class)
    public String getDate(String name,int age) {
        return name + age;
    }
}
