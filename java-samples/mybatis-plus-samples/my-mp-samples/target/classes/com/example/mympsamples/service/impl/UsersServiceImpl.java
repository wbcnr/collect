package com.example.mympsamples.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.mympsamples.entity.Users;
import com.example.mympsamples.mapper.UsersMapper;
import com.example.mympsamples.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author d
 * @since 2024-11-13
 */
//@DS("slave_1")
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    @Resource
    UsersMapper usersMapper;

    @Override
    public Users selectByName(String name) {
        Users users = usersMapper.selectByName(name);
        return users;
    }

    /**
     * @Transactional 和 @DS("slave_1") 两者同时使用是否会导致@DS失效？
     *
     * @param username
     * @param enabled
     * @return
     */
    @DS("slave_1")
    @Transactional
    @Override
    public int updateEnable(String username, String enabled) {
        int n = usersMapper.updateEnable(username, enabled);
        // 此处报错事务回滚
        //int i = 1/0;

        return n;
    }
}
