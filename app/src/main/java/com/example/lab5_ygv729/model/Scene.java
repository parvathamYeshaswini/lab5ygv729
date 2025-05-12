package com.example.lab5_ygv729.model;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private int number;
    private String title;
    private List<Role> roles;

    public Scene(int number, String title) {
        this.number = number;
        this.title = title;
        this.roles = new ArrayList<>();
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public List<Role> getRoles() {
        return roles;
    }

    public int getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }
}
