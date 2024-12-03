package com.example.mympsamples.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.mympsamples.entity.UserList;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * InnoDB free: 8192 kB 服务类
 * </p>
 *
 * @author d
 * @since 2024-11-04
 */
public interface IUserListService extends IService<UserList> {
    UserList  selectByUserId(String userid);

    int updateUserName(String userid, String username);


}
