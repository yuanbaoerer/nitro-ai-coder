package org.example.nitroaicoder.model.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.nitroaicoder.common.PageRequest;

import java.io.Serializable;

/**
 * 用户查询请求
 * 需要继承公共包中的 PageRequest 来支持分页查询
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryRequest extends PageRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 简介
     */
    private String userProfile;

    /**
     * 用户角色：user/admin/ban
     */
    private String userRole;

    private static final long serialVersionUID = 1L;
}

