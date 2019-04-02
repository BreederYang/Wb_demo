package com.mybaitspuls.demo.service;

import com.mybaitspuls.demo.entity.User;
import com.mybaitspuls.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public Integer insertUser(User u) {
        return userMapper.insertUser(u);
    }

    @Override
    public User getUser(User u) {
        return userMapper.getUser(u);
    }

    @Override
    public Integer updateUser(User u) {
        return userMapper.updateUser(u);
    }
}
