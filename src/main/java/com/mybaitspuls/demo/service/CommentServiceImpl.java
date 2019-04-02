package com.mybaitspuls.demo.service;

import com.mybaitspuls.demo.entity.Comment;
import com.mybaitspuls.demo.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public List<Comment> selectCommentByDid(Integer did) {
        return commentMapper.selectCommentByDid(did);
    }

    @Override
    public int addCommentByDid(Comment comment) {
        return commentMapper.addCommentByDid(comment);
    }
}
