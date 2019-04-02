package com.mybaitspuls.demo.controller;

import com.mybaitspuls.demo.entity.Comment;
import com.mybaitspuls.demo.entity.User;
import com.mybaitspuls.demo.service.CommentService;
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
@RequestMapping("/comment")
public class CommentController {
    public static Logger logger = LoggerFactory.getLogger(CommentController.class);
    @Resource
    private CommentService commentService;

    @RequestMapping("/findCommentByDid")
    @ResponseBody
    public List<Comment> findCommentByDid(Integer did){
        logger.info("用户请求：/comment/findCommentByDid,参数："+did);
        List<Comment> comments = commentService.selectCommentByDid(did);
        return comments;
    }
    @RequestMapping("/addCommentByDyid")
    @ResponseBody
    public int addCommentByDyid(String dynamicContext, Integer did, HttpSession session){
        logger.info("用户请求：/comment/addCommentByDyid,参数："+did);
        User user = (User) session.getAttribute(DictionaryUtils.session_user_auth);
        Comment comment = new Comment();
        comment.setContext(dynamicContext);
        comment.setUid(user.getId());
        comment.setuName(user.getUsername());
        comment.setDid(did);
        return commentService.addCommentByDid(comment);
    }
}
