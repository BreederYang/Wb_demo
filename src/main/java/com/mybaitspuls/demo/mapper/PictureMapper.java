package com.mybaitspuls.demo.mapper;

import com.mybaitspuls.demo.entity.Picture;

import java.util.List;

public interface PictureMapper {
    Integer addPicture(Picture picture);
    Picture getPictureById(Integer id);
    List<Picture> getPictureListInId(Integer [] ids);
}
