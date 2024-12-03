package com.example.mympsamples;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mympsamples.entity.UserList;
import com.example.mympsamples.mapper.UserListMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 代码生成器测试
 */
@SpringBootTest
public class MyMPCodeGeneratorTest {

    @Autowired(required = true)
    private UserListMapper userListMapper;
    @Autowired
    private UserList userList;

    @Test
    public void testCodeGenerator(){
        /**
         *
         */
    }

    /**
     *   直接使用 MyBatis-Plus 提供的 BaseMapper 接口中的方法，比如 selectById(), insert(), updateById(),
     *   deleteById() 等，简化了常见的 CRUD 操作。
     */
    @Test
    public void testSelectById(){
        UserList usr = userListMapper.selectById("admin");
        System.out.println("usr:" + usr);
    }
    /**
     * 通过wrapper查询
     */
    @Test
    public void selectByWrapper(){
        List<UserList> users = userListMapper.selectList(
                new QueryWrapper<UserList>().like("USER_ID", "admin")
        );
        //users.forEach(System.out::println);
        users.forEach(userList -> System.out.println("user:" + userList));

    }
}
