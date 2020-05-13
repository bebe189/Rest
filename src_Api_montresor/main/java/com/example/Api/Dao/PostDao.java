/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Api.Dao;

import com.example.Api.Domain.Post;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author richi
 */
public interface PostDao extends CrudRepository<Post, Long> {
    
}
