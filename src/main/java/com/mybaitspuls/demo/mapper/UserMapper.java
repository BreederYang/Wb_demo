package com.mybaitspuls.demo.mapper;

import com.mybaitspuls.demo.entity.User;

public interface UserMapper{

    Integer insertUser(User u);
    User getUser(User u);
    Integer updateUser(User u);

}
