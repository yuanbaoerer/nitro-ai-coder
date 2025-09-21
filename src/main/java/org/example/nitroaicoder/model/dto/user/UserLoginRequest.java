package org.example.nitroaicoder.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 接受登录请求参数的类
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;
}

