package com.mybaitspuls.demo.mapper;

import com.mybaitspuls.demo.entity.Dynamic;
import com.mybaitspuls.demo.entity.DynamicUserPicturePo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DynamicMapper {
    int createDynamic(Dynamic dynamic);
    int deleltDynamic(Integer id);
    List<DynamicUserPicturePo> selectDynamicByTime(@Param(value = "pageNumber") int pageNumber,@Param(value = "viewNumber") int viewNumber);
}
