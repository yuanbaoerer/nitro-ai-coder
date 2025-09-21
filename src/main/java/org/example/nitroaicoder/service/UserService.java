package org.example.nitroaicoder.service;

import com.mybatisflex.core.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import org.example.nitroaicoder.model.entity.User;
import org.example.nitroaicoder.model.vo.LoginUserVO;


/**
 *  服务层。
 *
 * @author <a href="https://github.com/yuanbaoerer">yuanbaoerer</a>
 */
public interface UserService extends IService<User> {
    /**
     *
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @return 脱敏后的用户信息
     */
    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 获取当前登录用户
     * @param request
     * @return 用户信息
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 用户注销
     * @param request 注销请求
     * @return  boolean 是否注销成功
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 获取脱敏的已登录用户信息
     * @param user 用户信息
     * @return 脱敏后的用户信息
     */
    LoginUserVO getLoginUserVO(User user);
}
