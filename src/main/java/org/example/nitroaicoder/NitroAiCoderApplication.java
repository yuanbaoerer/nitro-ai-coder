package org.example.nitroaicoder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(exposeProxy = true) // 通过 Spring AOP 提供对当前代理对象的访问，使得可以在业务逻辑中访问到当前的代理对象。可以在方法执行时通过 AopContext.currentProxy() 获取当前代理对象。
@SpringBootApplication
@MapperScan("org.example.nitroaicoder.mapper")
public class NitroAiCoderApplication {

    public static void main(String[] args) {
        SpringApplication.run(NitroAiCoderApplication.class, args);
    }

}
