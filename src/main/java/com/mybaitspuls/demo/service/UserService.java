package com.mybaitspuls.demo.service;

import com.mybaitspuls.demo.entity.User;

public interface UserService {
    Integer insertUser(User u);
    User getUser(User u);
    Integer updateUser(User u);
}
