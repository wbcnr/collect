package com.example.mympsamples.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * <p>
 * InnoDB free: 8192 kB
 * </p>
 *
 * @author d
 * @since 2024-11-04
 */
@Getter
@Setter
@ToString
@TableName("s_user_list")
@Component
public class UserList implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "USER_ID", type = IdType.ASSIGN_ID)
    private String userId;

    private String userName;

    private String password;

    private String pid;

    private String organId;
    //rank在新版本的mysql中是一个关键字
    @TableField("`RANK`")
    private Double rank;

    private String startDate;

    private String effectDate;

    private String state;

    private String workPhone;

    private String mobilePhone;

    private String noteAddress;

    private String eMail;

    private String id;

    private String filialeid;

    //逻辑删除字段
    @TableLogic
    private String triggerFlag;
//
//    @Override
//    public String toString() {
//        return "UserList{" +
//                "userId='" + userId + '\'' +
//                ", userName='" + userName + '\'' +
//                ", password='" + password + '\'' +
//                ", pid='" + pid + '\'' +
//                ", organId='" + organId + '\'' +
//                ", rank=" + rank +
//                ", startDate='" + startDate + '\'' +
//                ", effectDate='" + effectDate + '\'' +
//                ", state='" + state + '\'' +
//                ", workPhone='" + workPhone + '\'' +
//                ", mobilePhone='" + mobilePhone + '\'' +
//                ", noteAddress='" + noteAddress + '\'' +
//                ", eMail='" + eMail + '\'' +
//                ", id='" + id + '\'' +
//                ", filialeid='" + filialeid + '\'' +
//                ", triggerFlag='" + triggerFlag + '\'' +
//                '}';
//    }
}
