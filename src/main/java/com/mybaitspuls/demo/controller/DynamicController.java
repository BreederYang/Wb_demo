package com.mybaitspuls.demo.controller;

import com.mybaitspuls.demo.entity.Dynamic;
import com.mybaitspuls.demo.entity.DynamicUserPicturePo;
import com.mybaitspuls.demo.entity.Picture;
import com.mybaitspuls.demo.entity.User;
import com.mybaitspuls.demo.service.DynamicService;
import com.mybaitspuls.demo.service.PictureService;
import com.mybaitspuls.demo.utils.DictionaryUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/dy")
public class DynamicController {
    public static org.slf4j.Logger logger = LoggerFactory.getLogger(DynamicController.class);
    @Autowired
    private PictureService pictureService;
    @Autowired
    private DynamicService dynamicService;

    @RequestMapping("/goUpload")
    public String goUpload(){
        logger.info("用户请求：/dy/goUpload");
        return "ajaxupload";
    }
    @RequestMapping("/showIndexDynamic")
    public String showDynamic(Integer pageNumber,Integer viewNumber ,Model model){
        logger.info("用户请求：/dy/showIndexDynamic,参数：pageNumber"+pageNumber+",viewNumber:"+viewNumber);
//        设置默认分页参数
        if(pageNumber == null){
            pageNumber = 0;
        }
        if(viewNumber == null){
            viewNumber = 20;
        }
//        分页查询动态（0，20）
        List<DynamicUserPicturePo> dynamicUserPicturePos = dynamicService.selectDynamicByTime(pageNumber, viewNumber);
        if (dynamicUserPicturePos==null){
            model.addAttribute("msg","<a href='/dy/showIndexDynamic'>没有更多了,返回首页</a>");
        }
        model.addAttribute("dynamicPoList",dynamicUserPicturePos);
        model.addAttribute("pageNumber",pageNumber);
//        返回展示页面
        return "index";
    }

    /**
     * 前台接受
     * @param context
     * @param imageId
     * @param model
     * @return
     */
    @RequestMapping("/createDynamic")
    @ResponseBody
    public int createDynamic(String context,String imageId, Model model, HttpSession session){
        logger.info("用户请求：/dy/createDynamic,参数：context"+context+",imageId:"+imageId);
//        获取session 中用户信息 放入 dynamic 对象
        User user = (User) session.getAttribute(DictionaryUtils.session_user_auth);
        System.out.println(context+"----"+imageId);
        Dynamic dynamic = new Dynamic();
        dynamic.setUid(user.getId());
        dynamic.setUname(user.getUsername());
        dynamic.setContext(context);
        dynamic.setImageId(imageId);

        //        用户发表新动态
        int upCount = dynamicService.createDynamic(dynamic);
//        成功
        model.addAttribute("message","发表成功");
//        重定向到 展示页面（/dy/showIndexDynamic）
        return upCount;
    }

    /**
     *  一个支持ajax的上传方法，成功后返回图片的路径，可以即刻在页面回显图片，无刷新
     * @param mf
     * @param response
     * @param request
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @RequestMapping(value = "/ajaxupload", method = RequestMethod.POST,headers={})
    @ResponseBody
    public String ajaxupload(@RequestParam(value = "theimage", required = false) MultipartFile mf, HttpServletResponse response, HttpServletRequest request) throws IllegalStateException, IOException {
        logger.info("用户请求：/dy/ajaxupload");
        System.out.println("OK,进入方法");
        logger.info("绝对路径为："+request.getServletContext().getRealPath("/"));
        // 图片存放的物理路径
        //String filestr = request.getServletContext().getRealPath("/")+"upload/";
        String filestr = "/home/hadoop/upload/";
        logger.info("图片存放路径："+filestr);
        File file = new File(filestr);
        boolean exists = file.exists();
        if(!exists){
            logger.info("路径不存在，创建路径："+filestr);
            file.mkdirs();
        }
        // 图片的名称
        long imageName = System.currentTimeMillis()+(int)((Math.random() * 9 + 1) * 100000);
        // 写入图片
        try {
            mf.transferTo(new File(filestr + imageName + ".jpg"));
        }catch (FileNotFoundException e){
            logger.error("上传图片失败， 路径为："+filestr+",  图片名：" +imageName);
        }

        // 图片名写入数据库
        Picture picture = new Picture(imageName+".jpg");
        // 返回插入主键ID
        pictureService.addPicture(picture);
        System.out.println("OK");
        return imageName+ ".jpg"+","+picture.getId();
    }
}
