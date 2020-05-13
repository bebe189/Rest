/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Api.Dao;

import com.example.Api.Domain.BlogUser;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author richi
 */
public interface BlogUserDao extends CrudRepository<BlogUser, Long> {
    Optional<BlogUser> findByUsernameAndPassword(String username, String password);
}
