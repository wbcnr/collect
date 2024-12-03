package com.example.mympsamples.service;

import com.example.mympsamples.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author d
 * @since 2024-11-13
 */
public interface IUsersService extends IService<Users> {

    Users selectByName(String name);
    int updateEnable(@Param("username") String username, @Param("enabled") String enabled);

}
