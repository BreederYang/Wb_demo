package com.mybaitspuls.demo.service;

import com.mybaitspuls.demo.entity.Picture;
import com.mybaitspuls.demo.mapper.PictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureMapper pictureMapper;
    @Override
    public Integer addPicture(Picture picture) {
        return pictureMapper.addPicture(picture);
    }

    @Override
    public Picture getPictureById(Integer id) {
        return pictureMapper.getPictureById(id);
    }

    @Override
    public List<Picture> getPictureListInId(Integer[] ids) {
        return pictureMapper.getPictureListInId(ids);
    }
}
