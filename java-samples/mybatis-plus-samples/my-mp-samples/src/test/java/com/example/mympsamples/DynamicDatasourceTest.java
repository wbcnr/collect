package com.example.mympsamples;

import com.example.mympsamples.entity.UserList;
import com.example.mympsamples.entity.Users;
import com.example.mympsamples.service.IUserListService;
import com.example.mympsamples.service.IUsersService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 多数据源测试
 */
@Slf4j
@SpringBootTest
public class DynamicDatasourceTest {
    @Resource
    IUsersService usersService;
    @Resource
    IUserListService userListService;

    @Test
    public void tmpTest(){
        com.alibaba.druid.filter.config.ConfigTools configTools = null;
    }
    @Test
    public void  selectApiTest() {
        log.info("使用MP提供的CRUD");
        log.info("查询nacos-config中的users");
        Users user = usersService.getById("nacos");
        log.info(user.toString());
    }

    @Test
    public void selectMasterTest(){
        log.info("使用自定义的方法测试master库的查询.");
        UserList userList = userListService.selectByUserId("admin");
        if (userList.equals(null)){
            log.info("查询结果为");
        }
        log.info("查询到的结果userList是：{}", userList);
    }
    /**
     * 修改 sd_mis中的s_user_list
     * 注意：xml中的#{属性名}和mapper中的参数的对应关系
     */
    @Test
    public void updateMasterTest(){
        log.info("修改master数据库sd_mis中的s_user_list");
        int count = userListService.updateUserName("1", "衰人1111");
        log.info("修改记录数:{}", count);
    }

    /**
     * 测试事务是否还好使
     */
    @Test
    public void updateSalve1Test(){
        log.info("updateSalve1Test：测试事务是否还能生效");
        int n = usersService.updateEnable("nacos", "0");
        log.info("n = {}", n);
    }
}
