package com.example.mympsamples;

import com.example.mympsamples.entity.UserList;
import com.example.mympsamples.service.IUserListService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * p6spy测试
 * 这是一个用来测试数据库操作速度的框架
 */
@Slf4j
@SpringBootTest
public class P6spyTest {
    @Autowired
    IUserListService userListService;

    @Test
    public void testP6psy(){
        log.info("testP6psy start");
        String userId = "1";
        UserList userList = userListService.selectByUserId(userId);
        log.info("userList : {}", userList);
    }

}
