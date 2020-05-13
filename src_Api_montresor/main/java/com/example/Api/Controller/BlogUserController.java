/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Api.Controller;

import com.example.Api.Domain.BlogUser;
import com.example.Api.Service.BlogUserService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author richi
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class BlogUserController {
    @Autowired
    BlogUserService blogUserservice;
    
    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<jsonResponseBody> findUsers()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new jsonResponseBody(HttpStatus.OK.value(),blogUserservice.getUser()));
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<jsonResponseBody> findUserById(@PathVariable(value = "id") Long userId) {
        Optional<BlogUser> user = blogUserservice.getUserByID(userId);
        if (user.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new jsonResponseBody(HttpStatus.OK.value(),blogUserservice.getUserByID(userId)));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new jsonResponseBody(HttpStatus.NOT_FOUND.value(),null));
        }
        
    }
    
    @RequestMapping(method = RequestMethod.GET,params = {"username","password"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<jsonResponseBody> findUserByUsernameAndPassword(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password) {
        Optional<BlogUser> useru = blogUserservice.getUserByUsernameAndPassword(username, password);
        if(useru.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new jsonResponseBody(HttpStatus.OK.value(),blogUserservice.getUserByUsernameAndPassword(username, password)));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new jsonResponseBody(HttpStatus.NOT_FOUND.value(),null));
        }
    }
    
    @RequestMapping(method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<jsonResponseBody> addUser(HttpServletRequest request,@RequestBody BlogUser bu) 
    {
        BlogUser u = blogUserservice.insertUserService(bu);
        return ResponseEntity.status(HttpStatus.CREATED).header("location", request.getRequestURL().toString() + u.getId()).body(new jsonResponseBody(HttpStatus.CREATED.value(), null));
    }
    
    /*@DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }*/
    
    @AllArgsConstructor
    class jsonResponseBody{
        @Getter @Setter
        private int server;
        @Getter @Setter
        private Object response;
    }
}
