package com.mybaitspuls.demo.service;

import com.mybaitspuls.demo.entity.Discuss;
import com.mybaitspuls.demo.mapper.DiscussMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class DiscussServiceImpl implements DiscussService{
    @Autowired
    private DiscussMapper discussMapper;
    @Override
    public List<Discuss> selectDiscussByCid(Integer Cid) {
        return discussMapper.selectDiscussByCid(Cid);
    }

    @Override
    public int addDiscussByDid(Discuss discuss) {
        return discussMapper.addDiscussByDid(discuss);
    }
}
