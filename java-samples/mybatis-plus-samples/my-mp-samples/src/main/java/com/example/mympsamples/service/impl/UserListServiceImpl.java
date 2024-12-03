package com.example.mympsamples.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mympsamples.entity.UserList;
import com.example.mympsamples.mapper.UserListMapper;
import com.example.mympsamples.service.IUserListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * InnoDB free: 8192 kB 服务实现类
 * </p>
 *
 * @author d
 * @since 2024-11-04
 */
@Slf4j
@DS("master")
@Service
public class UserListServiceImpl extends ServiceImpl<UserListMapper, UserList> implements IUserListService {

    @Resource
    UserListMapper userListMapper;

    @Override
    public UserList selectByUserId(String userid) {
        return userListMapper.selectByUserId(userid);
    }

    /**
     * 测试 @Transactional 与 @DS同时使用时是否会使DS失效
     * @param userid
     * @param username
     * @return
     */
    @Transactional
    @Override
    public int updateUserName(String userid, String username) {
        int n =  userListMapper.updateUserName(userid, username);
        log.info("n = {}", n);
//        int i = 1 / 0 ;
        log.info("这里应该会发生异常!");
        return n;
    }
}
