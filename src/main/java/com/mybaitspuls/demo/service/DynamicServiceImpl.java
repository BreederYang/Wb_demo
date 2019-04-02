package com.mybaitspuls.demo.service;

import com.mybaitspuls.demo.entity.Dynamic;
import com.mybaitspuls.demo.entity.DynamicUserPicturePo;
import com.mybaitspuls.demo.mapper.DynamicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DynamicServiceImpl implements DynamicService {
    @Autowired
    private DynamicMapper dynamicMapper;
    @Override
    public int createDynamic(Dynamic dynamic) {
        return dynamicMapper.createDynamic(dynamic);
    }

    @Override
    public int deleltDynamic(Integer id) {
        return dynamicMapper.deleltDynamic(id);
    }

    @Override
    public List<DynamicUserPicturePo> selectDynamicByTime(int pageNumber, int viewNumber) {
        return dynamicMapper.selectDynamicByTime(pageNumber,viewNumber);
    }
}
