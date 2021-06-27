package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Builder;
import lombok.Data;
import sun.study.note.MainApplication;
import sun.study.note.mapper.UserMapper;
import sun.study.note.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.study.note.spring.service.UserService;

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

    @Autowired
    private UserService userService;

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

        IPage<PageUser> pageUserIPage = new Page<>();
        pageUserIPage.setPages(userIPage.getPages());
        pageUserIPage.setCurrent(userIPage.getCurrent());

    }

    @Data
    @Builder
    static class PageUser{
        private String name;
        private Integer age;
        private String email;
    }

    @Test
    public void update(){
        int result = userMapper.update(User.builder().age(999).build(), new UpdateWrapper<User>().eq("id", 1));
        System.out.println(result);
    }
    @Test
    public void save(){
        User u = User.builder().age(1111).email("sun@q123.com").name("Smart-hand")
                .build();

        userService.save(u);
        System.out.println(u.getId());
    }

    @Test
    public void updateBatch(){
        Page<User> page = new Page<>(1,2);
        IPage<User> userIPage = userMapper.selectPage(page, null);

        List<User> records = userIPage.getRecords();

        for (User u : records){
            u.setAge(22224);
        }

        records.add(User.builder().id(9l).age(2222).email("sun@q123.com").name("Smart-hand")
                .build());

        boolean b = userService.saveOrUpdateBatch(records);
        System.out.println(b);
    }

   /* @Test
    public void testInsert() {
        User u = new User();
        u.setAge(11);
        u.setEmail("ab@qwe");
        u.setName("zhangsan");
        int insert = userMapper.insert(u);
        System.out.println(u.getId());

    }*/
}
