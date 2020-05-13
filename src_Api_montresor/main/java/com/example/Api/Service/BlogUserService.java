/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Api.Service;

import com.example.Api.Dao.BlogUserDao;
import com.example.Api.Domain.BlogUser;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author richi
 */
@Service
public class BlogUserService {

    @Autowired
    BlogUserDao blogUserdao;

    public Optional<BlogUser> getUserByUsernameAndPassword(String username, String password) 
    {
        return blogUserdao.findByUsernameAndPassword(username, password);
       
    }
    
    public Optional<BlogUser> getUserByID(Long id)
    {
        return blogUserdao.findById(id);
    }

    public List<BlogUser> getUser() 
    {
        return (List<BlogUser>) blogUserdao.findAll();

    }

    public BlogUser insertUserService(BlogUser bu) 
    {
        return blogUserdao.save(bu);
    }
    /*public void deleteUserService(BlogUser bu)
    {
        blogUserdao.delete(bu);
    }*/

}
