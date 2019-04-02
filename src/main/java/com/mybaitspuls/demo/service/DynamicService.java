package com.mybaitspuls.demo.service;

import com.mybaitspuls.demo.entity.Dynamic;
import com.mybaitspuls.demo.entity.DynamicUserPicturePo;

import java.util.List;

public interface DynamicService {
//    发布动态
    int createDynamic(Dynamic dynamic);
//    删除动态（1.删除相关的评论，2.删除动态，3. 删除相关图片）
    int deleltDynamic(Integer id);
    List<DynamicUserPicturePo> selectDynamicByTime(int pageNumber, int viewNumber);
}
