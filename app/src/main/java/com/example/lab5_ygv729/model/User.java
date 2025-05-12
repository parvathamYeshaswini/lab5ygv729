package com.example.lab5_ygv729.model;

import android.content.Context;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String username;
    private String password;
    private String realName;
    private List<Role> roles;

    public User(String username, String password, String realName, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.roles = roles;
    }

    public static User validate(Context context, String inputUsername, String inputPassword) {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("users.csv"))
            );
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String username = parts[0];
                    String password = parts[1];
                    String realName = parts[2];
                    if (inputUsername.equals(username) && inputPassword.equals(password)) {
                        List<Role> roles = new ArrayList<>();
                        for (int i = 3; i < parts.length; i++) {
                            roles.add(new Role(parts[i]));
                        }
                        return new User(username, password, realName, roles);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getRealName() {
        return realName;
    }

    public List<Role> getRoles() {
        return roles;
    }
}
