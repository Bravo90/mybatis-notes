package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import sun.study.note.MainApplication;
import sun.study.note.mapper.UserMapper;
import sun.study.note.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author sunzhen <sunzhen03@kuaishou.com>
 * Created on 2021-06-18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class SampleTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name","Jone");

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void testSelectPage() {
        Page<User> page = new Page<>(1,2);
        IPage<User> userIPage = userMapper.selectPage(page, null);
        List<User> records = userIPage.getRecords();
        records.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        User u = new User();
        u.setAge(11);
        u.setEmail("ab@qwe");
        u.setName("zhangsan");
        int insert = userMapper.insert(u);
        System.out.println(u.getId());

    }
}
