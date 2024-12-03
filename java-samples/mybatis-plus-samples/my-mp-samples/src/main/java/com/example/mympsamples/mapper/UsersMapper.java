package com.example.mympsamples.mapper;

import com.example.mympsamples.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author d
 * @since 2024-11-13
 */
public interface UsersMapper extends BaseMapper<Users> {

    /**
     *
     * @param name
     * @return
     */
    Users selectByName(String name);

    /**
     *
     * @param username
     * @param enabled
     * @return
     */
    int updateEnable(@Param("username") String username, @Param("enabled") String enabled);
}
