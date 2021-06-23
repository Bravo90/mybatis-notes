package sun.study.note.spring.controller;

import sun.study.note.mapper.UserMapper;
import sun.study.note.model.User;
import sun.study.note.spring.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    @GetMapping("config")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户标识", required = true, paramType = "form",dataType = "Integer")
    })
    @ApiOperation("获取user信息")
    public String getMsg(int id) {
        System.out.println(userMapper);
        User user = userMapper.selectById(id);
        System.out.println(user);
        return user.toString();
    }


    @GetMapping("getDate")
    public String getDate(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-dd-MM HH:mm:ss") Date startDate){
        System.out.println(startDate);
        return "";
    }
}
