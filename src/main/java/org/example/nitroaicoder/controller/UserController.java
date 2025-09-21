package org.example.nitroaicoder.controller;

import com.mybatisflex.core.paginate.Page;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.example.nitroaicoder.common.BaseResponse;
import org.example.nitroaicoder.common.ResultUtils;
import org.example.nitroaicoder.exception.ErrorCode;
import org.example.nitroaicoder.exception.ThrowUtils;
import org.example.nitroaicoder.model.dto.user.UserLoginRequest;
import org.example.nitroaicoder.model.dto.user.UserRegisterRequest;
import org.example.nitroaicoder.model.entity.User;
import org.example.nitroaicoder.model.vo.LoginUserVO;
import org.example.nitroaicoder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  控制层。
 *
 * @author <a href="https://github.com/yuanbaoerer">yuanbaoerer</a>
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     *
     * @param userRegisterRequest 用户注册请求
     * @return 注册结果
     */
    @PostMapping("register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        ThrowUtils.throwIf(userRegisterRequest == null, ErrorCode.PARAMS_ERROR);
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(result);
    }

    /**
     * 用户登录
     * @param userLoginRequest 登录请求
     * @param request 登录请求
     * @return 返回登录成功码
     */
    @PostMapping("/login")
    public BaseResponse<LoginUserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(userLoginRequest == null, ErrorCode.PARAMS_ERROR);
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        LoginUserVO loginUserVO = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(loginUserVO);
    }

    /**
     * 用户注销
     *
     * @param request 注销请求
     * @return 退出登录
     */
    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR);
        boolean result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    /**
     * 获取当前登录用户，
     * @param request
     * @return 返回脱敏后的用户信息
     */
    @GetMapping("/get/login")
    public BaseResponse<LoginUserVO> getLoginUser(HttpServletRequest request){
        User loginUser = userService.getLoginUser(request);

        return ResultUtils.success(userService.getLoginUserVO(loginUser));
    }
}
