package org.example.nitroaicoder.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;

import org.example.nitroaicoder.mapper.UserMapper;
import org.example.nitroaicoder.model.entity.User;
import org.example.nitroaicoder.service.UserService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author <a href="https://github.com/yuanbaoerer">yuanbaoerer</a>
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>  implements UserService {

}
