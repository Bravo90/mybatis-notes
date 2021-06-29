package sun.study.note.spring.controller;

import org.springframework.web.bind.annotation.*;
import sun.study.note.spring.anno.DataAuth;
import sun.study.note.mapper.UserMapper;
import sun.study.note.model.User;
import sun.study.note.spring.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

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
    @DataAuth(targetParamName = "user", targetParamClassMethodName = "getName")
    public String getMsg(@RequestBody User user) {
        return user.toString();
    }

    @GetMapping("getDate")
    @DataAuth(targetParamName = "name")
    public String getDate(String name, int age) {
        return name + age;
    }
}
