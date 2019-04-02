package com.mybaitspuls.demo.mapper;

import com.mybaitspuls.demo.entity.Comment;

import java.util.List;

/**
 * 评论接口
 */
public interface CommentMapper {
//    根据 动态 ID 查询关联的 评论
    List<Comment> selectCommentByDid(Integer did);
//    添加评论
    int addCommentByDid(Comment comment);
}
