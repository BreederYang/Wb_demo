package com.mybaitspuls.demo.mapper;

import com.mybaitspuls.demo.entity.Discuss;

import java.util.List;

/**
 * 讨论接口
 */
public interface DiscussMapper {
//     查看讨论
    List<Discuss> selectDiscussByCid(Integer Cid);
//     添加讨论
    int addDiscussByDid(Discuss discuss);
}
