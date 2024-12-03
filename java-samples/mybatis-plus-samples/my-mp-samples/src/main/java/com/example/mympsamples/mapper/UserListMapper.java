package com.example.mympsamples.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.mympsamples.entity.UserList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * InnoDB free: 8192 kB Mapper 接口
 * </p>
 *
 * @author d
 * @since 2024-11-04
 */
@Mapper
public interface UserListMapper extends BaseMapper<UserList> {
    // ${ew.customSqlSegment} 这是固定写法
    @Select("SELECT * FROM S_USER_LIST ${ew.customSqlSegment}")
    List<UserList> selectAll(@Param(Constants.WRAPPER) Wrapper<UserList> wrapper);

    /**
     * 使用xml方式注入sql
     * @param wrapper
     * @return
     */
    List<UserList> selectAllWithXML(@Param(Constants.WRAPPER) Wrapper<UserList> wrapper);

    UserList selectByUserId(String userid);

    int updateUserName(@Param("userid") String userid, @Param("username") String username);
}
