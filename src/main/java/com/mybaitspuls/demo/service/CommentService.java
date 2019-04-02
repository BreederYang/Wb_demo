package com.mybaitspuls.demo.service;

import com.mybaitspuls.demo.entity.Comment;

import java.util.List;

public interface CommentService {
    //    根据 动态 ID 查询关联的 评论
    List<Comment> selectCommentByDid(Integer did);
    //    添加评论
    int addCommentByDid(Comment comment);
}
