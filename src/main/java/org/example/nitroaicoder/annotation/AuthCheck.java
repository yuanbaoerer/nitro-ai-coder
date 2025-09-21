package org.example.nitroaicoder.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限校验
 * 自定义注解的定义，用于权限校验相关功能
 *
 * 只要给方法添加了 @AuthCheck 注解，就会进行权限校验（限制了只能用于接口上）
 * 例如 @AuthCheck(mustRole = UserConstant.ADMIN_ROLE）这样就只有管理员能使用该接口
 * 对于不需要登录就能使用的接口，不需要使用该接口
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {
    /**
     * 必须有某个角色
     *
     * @return
     */
    String mustRole() default "";
}
