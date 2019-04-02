package com.mybaitspuls.demo.controller;

import com.mybaitspuls.demo.entity.Picture;
import com.mybaitspuls.demo.service.PictureService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/img")
public class PictureController {
    public static org.slf4j.Logger logger = LoggerFactory.getLogger(PictureController.class);

    @Autowired
    private PictureService pictureService;

    @RequestMapping("/findPictureInIds")
    @ResponseBody
    public List<Picture> findPictureInIds(String ids){
        logger.info("用户请求：/img/findPictureInIds,参数：ids:"+ids);
        Integer [] Intids = new Integer[ids.split(",").length];
        for(int i=0;i<ids.split(",").length;i++){
            Intids[i] = Integer.parseInt(ids.split(",")[i]);
        }
        List<Picture> pictureListInId = pictureService.getPictureListInId(Intids);
        return pictureListInId;
    }

}
