package com.example.mympsamples.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 
 * </p>
 *
 * @author d
 * @since 2024-11-13
 */
@Getter
@Setter
@ToString
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * username
     */
    @TableId("username")
    private String username;

    /**
     * password
     */
    private String password;

    /**
     * enabled
     */
    private Boolean enabled;
}
