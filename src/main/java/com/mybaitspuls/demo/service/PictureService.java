package com.mybaitspuls.demo.service;

import com.mybaitspuls.demo.entity.Picture;

import java.util.List;

public interface PictureService {
    Integer addPicture(Picture picture);
    Picture getPictureById(Integer id);
    List<Picture> getPictureListInId(Integer [] ids);
}
