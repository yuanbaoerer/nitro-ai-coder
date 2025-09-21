package org.example.nitroaicoder.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import org.example.nitroaicoder.model.dto.user.UserQueryRequest;
import org.example.nitroaicoder.model.entity.User;
import org.example.nitroaicoder.model.vo.LoginUserVO;
import org.example.nitroaicoder.model.vo.UserVO;

import java.util.List;


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

    /**
     * 获取脱敏的用户信息
     * @param user 用户信息
     * @return 脱敏后的单个用户信息
     */
    UserVO getUserVO(User user);

    /**
     * 获取脱敏的用户信息列表
     * @param userList 用户列表
     * @return 脱敏后的用户列表
     */
    List<UserVO> getUserVOList(List<User> userList);

    /**
     * 将查询请求转为 QueryWrapper 对象
     * @param userQueryRequest 查询请求
     * @return 查询对象
     */
    QueryWrapper getQueryWrapper(UserQueryRequest userQueryRequest);

    /**
     * 获取加密后的密码
     * @param userPassword 密码
     * @return 加密后的密码
     */
    String getEncryptPassword(String userPassword);
}
