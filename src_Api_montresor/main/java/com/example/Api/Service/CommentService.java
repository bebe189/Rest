/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Api.Service;

import com.example.Api.Dao.CommentDao;
import com.example.Api.Domain.Comment;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author richi
 */
@Service
public class CommentService {
    
    @Autowired
    CommentDao commentDao;
    
    public List<Comment> getCommentAll()
    {
        return (List<Comment>) commentDao.findAll();
    }
    public Optional<Comment> getCommentById(Long id)
    {
        return commentDao.findById(id);
    }
    
    public Comment insertCommentService(Comment c)
    {
        return commentDao.save(c);
    }
}
