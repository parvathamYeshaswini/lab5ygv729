package com.example.lab5_ygv729.model;

import java.io.Serializable;

public class Role implements Serializable {
    private String name;

    public Role(String name) {
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Role) {
            return name.equalsIgnoreCase(((Role) obj).getName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }
}
