package org.example.nitroaicoder.exception;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.example.nitroaicoder.common.BaseResponse;
import org.example.nitroaicoder.common.ResultUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 防止意料之外的异常，利用 AOP 切面全局对业务异常和 RuntimeException 进行捕获
 */
@Hidden // 本项目 Springboot >= 3.4，并且是 OpenAPI 3版本的 Knife4j，则需要添加 @Hidden 注解，否则 Knife4j 会将本类加入到文档中。
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e)
    {
        log.error("businessExceptionHandler", e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e){
        log.error("runtimeExceptionHandler", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "系统错误");
    }
}
