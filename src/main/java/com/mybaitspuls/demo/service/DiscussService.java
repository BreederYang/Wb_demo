package com.mybaitspuls.demo.service;

import com.mybaitspuls.demo.entity.Discuss;

import java.util.List;

public interface DiscussService {
    //     查看讨论
    List<Discuss> selectDiscussByCid(Integer Cid);
    //     添加讨论
    int addDiscussByDid(Discuss discuss);
}
