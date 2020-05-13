/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Api.Service;

import com.example.Api.Dao.PostDao;
import com.example.Api.Domain.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author richi
 */
@Service
public class PostService {
    
    @Autowired
    PostDao postdao;
    
    public List<Post> getPostAll()
    {
        return (List<Post>) postdao.findAll();
    }
    public Optional<Post> getPostById(Long id)
    {
        return postdao.findById(id);
    }
    public Post insertPostService(Post p)
    {
        return postdao.save(p);
    }
}
