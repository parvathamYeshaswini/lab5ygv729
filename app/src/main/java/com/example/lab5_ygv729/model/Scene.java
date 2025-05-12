package com.example.lab5_ygv729.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Scene implements Serializable {
    private int number;
    private String title;
    private List<Role> roles;

    public Scene(int number, String title) {
        this.number = number;
        this.title = title;
        this.roles = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        roles.add(role);
    }
}
