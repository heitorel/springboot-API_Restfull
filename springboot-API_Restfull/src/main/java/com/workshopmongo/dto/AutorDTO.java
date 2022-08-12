package com.workshopmongo.dto;

import com.workshopmongo.domanin.User;

import java.io.Serializable;

public class AutorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;

    public AutorDTO() {
    }

    public AutorDTO(User user){
        this.id = user.getId();
        this.name = user.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
