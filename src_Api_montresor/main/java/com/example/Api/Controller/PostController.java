/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Api.Controller;

import com.example.Api.Domain.Post;
import com.example.Api.Service.PostService;
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

/**
 *
 * @author richi
 */
@RestController
@RequestMapping("/posts")
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class PostController {
    @Autowired
    PostService postService;
    
    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<jsonResponseBody> findPostAll() {
        return ResponseEntity.status(HttpStatus.OK).body(new jsonResponseBody(HttpStatus.OK.value(),postService.getPostAll()));
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<jsonResponseBody> findPostById(@PathVariable(value = "id") Long id) {
        Optional<Post> p = postService.getPostById(id);
        if (p.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new jsonResponseBody(HttpStatus.OK.value(),postService.getPostById(id)));
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new jsonResponseBody(HttpStatus.NOT_FOUND.value(),null));
        }
    }
    
    
    
    @RequestMapping(method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<jsonResponseBody> addPost(HttpServletRequest request,@RequestBody Post p) 
    {
        Post po = postService.insertPostService(p);
        return ResponseEntity.status(HttpStatus.CREATED).header("location", request.getRequestURL().toString() + po.getId()).body(new jsonResponseBody(HttpStatus.CREATED.value(), null));
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
