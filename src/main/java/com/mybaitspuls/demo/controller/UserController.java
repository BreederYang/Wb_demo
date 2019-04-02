package com.mybaitspuls.demo.controller;



import com.mybaitspuls.demo.entity.Picture;
import com.mybaitspuls.demo.entity.User;
import com.mybaitspuls.demo.mapper.UserMapper;
import com.mybaitspuls.demo.service.PictureService;
import com.mybaitspuls.demo.service.UserService;
import com.mybaitspuls.demo.utils.DictionaryUtils;
import com.mybaitspuls.demo.utils.MD5Utils;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.GenericServlet;
import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    public static Logger logger = LoggerFactory.getLogger(CommentController.class);
    @Autowired
    private UserService service;
    @Autowired
    private PictureService pictureService;
    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping("/toLogin")
    public String go(){
        logger.info("用户请求：/user/toLogin");
        return "login";
    }
    @RequestMapping("/toRegister")
    public String toRegister(){
        logger.info("用户请求：/user/toRegister");
        return "register";
    }

    /**
     * 用户注册
     * @param user
     * @param model
     * @return
     */
    @RequestMapping("/register")
    public String insertUser(User user,Model model){
        logger.info("用户请求：/user/register 参数：User :"+user.toString());
//        注册
//        md5 密码加密
        String md5Psw = MD5Utils.MD5Encode(user.getPassword(), "UTF-8");
        user.setPassword(md5Psw);
        System.out.println("加密后密码："+user.getPassword());
        Integer insert_count = service.insertUser(user);
//        成功 跳转登录页面
        if (insert_count>0){
            return "login";
        }
        model.addAttribute("error","注册失败，请重新注册");
        return "register";
    }

    /**
     * 登录方法
     * @param email
     * @param password
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(String email,String password, HttpSession session,Model model){
        logger.info("用户请求：/user/login 参数：email :"+email+",password:"+password);
        User u = new User();
        //        md5 密码加密
        String md5Psw = MD5Utils.MD5Encode(password, "UTF-8");
        u.setEmail(email);
        u.setPassword(md5Psw);
//        查询db
        User user = service.getUser(u);
        if (user!= null){
//            放入session
            session.setAttribute(DictionaryUtils.session_user_auth,user);
//            重定向到展示言论
            return "redirect:/dy/showIndexDynamic";
        }
        model.addAttribute("error","账号 or 密码不正确，请重新登录。");
        return "login";
    }

    /**
     * 用户登出 清除 session 中 user 信息
     * @param session
     * @return
     */
    @RequestMapping("/goOut")
    public String goOut(HttpSession session){
        logger.info("用户请求：/user/goOut");
        session.removeAttribute(DictionaryUtils.session_user_auth);//清空session信息
//        跳转登录页面
        return "login";
    }

    @RequestMapping("/showUserInformation")
    public String showUserInformation(Integer id, Model model){
        logger.info("用户请求：/user/showUserInformation,参数："+id);
        User user = service.getUser(new User(id));
        Picture pictureById = pictureService.getPictureById(Integer.valueOf(user.getPortrait()));
        model.addAttribute("userPictuer",pictureById.getName());
        model.addAttribute("userInfo",user);
        return "userInfo";
    }
    @RequestMapping("/showMyInfo")
    public String showUserInformation(HttpSession session, Model model){
        logger.info("用户请求：/user/showMyInfo,参数：根据session中用户信息查询头像地址");
        User sessionUser = (User)session.getAttribute(DictionaryUtils.session_user_auth);
        Picture pictureById;
        try {
            pictureById = pictureService.getPictureById(Integer.valueOf(sessionUser.getPortrait()));
            if (pictureById!=null){
                model.addAttribute("sessionUserPicture",pictureById.getName());
            }
        }catch (NullPointerException n){
            model.addAttribute("sessionUserPicture","defautPicture.jpg");
        }


        return "myInfo";
    }
    @RequestMapping("/upMyInfo")
    public String upMyInfo(User user,HttpSession session){
        logger.info("用户请求：/user/upMyInfo,参数：User"+user.toString()+"修改完成更新session 信息");
        Integer integer = service.updateUser(user);
        User newUser = service.getUser(user);
        session.setAttribute(DictionaryUtils.session_user_auth,newUser);
        return "redirect:/dy/showIndexDynamic";
    }
}
