package com.example.mympsamples;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mympsamples.entity.UserList;
import com.example.mympsamples.mapper.UserListMapper;
import com.example.mympsamples.service.IUserListService;
import com.example.mympsamples.service.IUsersService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
class MyMpSamplesApplicationTests {

    @Resource
    UserListMapper userListMapper;
    //sd_mis数据库
    @Autowired
    IUserListService userListService;
    //nacos-config
    @Resource
    IUsersService usersService;


    @Test
    void contextLoads() {
        DataSourceBuilder dataSourceBuilder = null;
        ConfigurationPropertiesBindingPostProcessor a = null;
    }

    /**
     * 测试多数据源 springboot的多数据源
     */
    /**
     * 测试多数据源：dynamic-datasource方式
     */
    @Test
    public void testDynamicDatasource(){

    }

    /**
     * 逻辑删除， 删除：0
     */
    @Test
    public void testLogicDel(){
        String strDel = "0", strNotDel = "1";
        LambdaQueryWrapper<UserList> queryWrapper= new LambdaQueryWrapper<>();
        queryWrapper.like(UserList::getUserName, "靓仔")
                    .orderByDesc(UserList::getUserId);
        log.info("------------------------------------------------deleteById--------------------------------------------------------");
        int n = userListMapper.deleteById("1");
        log.info("n = {}", n);
        log.info("------------------------------------------------selectPage--------------------------------------------------------");
        Page<UserList> page = new Page<>(1,2);
        IPage<UserList> iPage = userListMapper.selectPage(page,queryWrapper);
        //IPage<UserList> iPage = userListMapper.selectPage(page,queryWrapper);
        log.info("总页数: " + iPage.getPages());
        log.info("总个数: " + iPage.getTotal());
        List<UserList> userList = iPage.getRecords();
        userList.forEach(System.out::println);
        String classNameOfIPage = iPage.getClass().getName();
        log.info("classNameOfIPage = {}", classNameOfIPage);

    }
    /**
     * 使用xml注入sql
     * ResultMap?
     */
    @Test
    public void selectByWrapperXMLSQL1(){
        System.out.println(" 使用xml注入sql，需要使用ResultMap？");
        LambdaQueryWrapper<UserList> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(UserList::getUserName, "管理员");
        List<UserList> userList = userListMapper.selectAllWithXML(queryWrapper);

        userList.forEach(System.out::println);
    }
    /**
     * 使用xml注入sql
     */
    @Test
    public void selectByWrapperXMLSQL(){
        System.out.println(" 使用xml注入sql");
        LambdaQueryWrapper<UserList> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserList::getUserId, "admin");
        List<UserList> userList = userListMapper.selectAllWithXML(queryWrapper);

        userList.forEach(System.out::println);
    }
    /**
     * 通过注解注入sql语句
     */
    @Test
    public void selectByWrapperSQL() {
        System.out.println("通过注解注入sql语句");
        LambdaUpdateWrapper<UserList> queryWrapper = new LambdaUpdateWrapper<>();
        queryWrapper.like(UserList::getUserName, "管理员");
        List<UserList> userList = userListMapper.selectAll(queryWrapper);

        userList.forEach(System.out::println);
    }

    /**
     * 通过wrapper查询
     */
    @Test
    public void selectByWrapper2(){
        UserList userList = new UserList();
        userList.setId("admin");
        QueryWrapper<UserList> queryWrapper = new QueryWrapper<>();
        List<UserList> users = userListMapper.selectList(
                new QueryWrapper<UserList>().like("USER_ID", "admin")
        );
        //users.forEach(System.out::println);
        users.forEach(userList1 -> System.out.println("user:" + userList1));

    }

    /**
     * 给entity赋值
     *  可行
     *  不好
     *
     */
    @Test
    public void selectByLambdaQueryWrapper(){
        System.out.println("selectByLambdaQueryWrapper:");
        UserList userList = new UserList();
        userList.setUserId("admin");
        LambdaQueryWrapper<UserList> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.likeRight(UserList::getUserName, "靓仔");
        List<UserList> users = userListMapper.selectList(queryWrapper);
        users.forEach(System.out::println);

    }

    /**
     * 测试LambdaUpdateWrapper 插入
     */
    @Test
    public void testLambdaUpdateWrappe1(){
        System.out.println("testLambdaUpdateWrappe1:删除所有靓仔");
        //1
        UserList userList = new UserList();
        userList.setPassword("123456");
        //2
        LambdaUpdateWrapper<UserList> userLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        userLambdaUpdateWrapper.likeRight(UserList::getUserName, "靓仔");
        //3
        //int count = userListMapper.update(userList,userLambdaUpdateWrapper);
        int count = userListMapper.delete(userLambdaUpdateWrapper);
        //4
        System.out.println("已经修改: " + count + " 条");

    }

    /**
     * 测试LambdaUpdateWrapper
     */
    @Test
    public void testLambdaUpdateWrapper(){
        //1
        UserList userList = new UserList();
        userList.setUserName("超级管理员");
        //2
        LambdaUpdateWrapper<UserList> userLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        userLambdaUpdateWrapper.eq(UserList::getUserId, "test2");
        //3
        int count = userListMapper.update(userList,userLambdaUpdateWrapper);
        //4
        System.out.println("已经修改: " + count + " 条");

    }
    /**
     * LambdaQueryWrapper.eq()
     */
    @Test
    public void selectByWrapper1(){
        System.out.println("selectByWrapper1:测试LambdaQueryWrapper：");
        LambdaQueryWrapper<UserList> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserList::getUserId, "admin");
        List<UserList> users = userListMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
        users.forEach(userList -> System.out.println("user:" + userList));

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

    /**
     * 测试自定义生成主键
     */
    @Test
    void testCustomIdGenerator(){
        System.out.println("MyMpSamplesApplicationTests.testCustomIdGenerator: 测试主键生成器。");
        UserList user = new UserList();
        user.setUserName("靓仔");
        user.setEMail("" + 18);
        userListMapper.insert(user);
        //Assertions.assertEquals(Long.valueOf(1L), user.getId());
        System.out.println("user.getId():" + user.getId());
        testBatch();
    }

    /**
     * 批量插入
     */
    public void testBatch() {
        List<UserList> users = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            UserList user = new UserList();
            user.setUserName("靓仔" + i);
            user.setEMail("" + 18 + i);
            users.add(user);
        }
        boolean result = userListService.saveBatch(users);
        Assertions.assertEquals(true, result);
    }

}
