package com.mybaitspuls.demo.controller;

import com.mybaitspuls.demo.entity.Discuss;
import com.mybaitspuls.demo.entity.User;
import com.mybaitspuls.demo.service.DiscussService;
import com.mybaitspuls.demo.utils.DictionaryUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/discuss")
public class DiscussController {
    public static Logger logger = LoggerFactory.getLogger(DiscussController.class);
    @Autowired
    private DiscussService discussService;

    @RequestMapping("/findDiscussByCid")
    @ResponseBody
    public List<Discuss> findDiscussByCid(Integer cid){
        logger.info("用户请求：/discuss/findDiscussByCid,参数 cid :"+cid);
        List<Discuss> discusses = discussService.selectDiscussByCid(cid);
        return discusses;
    }
    @RequestMapping("/addDiscussByCid")
    @ResponseBody
    public int addDiscussByCid(String discussContext, Integer cid, HttpSession session){
        logger.info("用户请求：/discuss/addDiscussByCid,参数 discussContext :"+discussContext+",cid:"+cid);
        User user = (User) session.getAttribute(DictionaryUtils.session_user_auth);
        Discuss discuss = new Discuss();
        discuss.setCid(cid);
        discuss.setContext(discussContext);
        discuss.setUid(user.getId());
        discuss.setuName(user.getUsername());
        return discussService.addDiscussByDid(discuss);
    }
}
