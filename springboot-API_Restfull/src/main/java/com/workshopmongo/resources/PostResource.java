package com.workshopmongo.resources;

import com.workshopmongo.domanin.Post;
import com.workshopmongo.resources.util.URL;
import com.workshopmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @GetMapping
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Post> findById(@PathVariable String id){
        Post user = service.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping
    @RequestMapping(value = "/{id}/titlesearch", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findByTitle
            (@RequestParam(value = "text", defaultValue = "") String text) {

        text = URL.decodeParam(text);
        List<Post> list = service.findByTitle(text);
        return ResponseEntity.ok().body(list);
    }

}
