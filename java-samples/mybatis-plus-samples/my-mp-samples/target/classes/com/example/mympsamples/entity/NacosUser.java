package com.example.mympsamples.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.ToString;
import org.springframework.stereotype.Component;

@ToString
@TableName("users")
@Component
public class NacosUser {
    private static final long serialVersionUID = 1L;

    @TableId( type = IdType.INPUT)
    private String userName;
    private String passWord;
    @TableLogic
    private int enable;

}
