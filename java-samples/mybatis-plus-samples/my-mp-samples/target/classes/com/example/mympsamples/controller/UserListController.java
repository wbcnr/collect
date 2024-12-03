package com.example.mympsamples.controller;

import com.example.mympsamples.entity.UserList;
import com.example.mympsamples.service.IUserListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * InnoDB free: 8192 kB 前端控制器
 * </p>
 *
 * @author d
 * @since 2024-11-04
 */
@Slf4j
@Controller
@RequestMapping("/userList")
public class UserListController {

    @Autowired
    private IUserListService userListService;

    // 测试地址 http://localhost:8080/mympsamples/userList/test
    @RequestMapping(value = "test", method = RequestMethod.GET)
    @ResponseBody
    public String getUserById(){
        String userId = "1";
        UserList userList = userListService.selectByUserId(userId);
        log.info("userList : {}", userList);
        return userList.toString();
    }

}
