package com.example.lab5_ygv729.model;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private String realName;
    private List<Role> roles;

    public User(String username, String password, String realName) {
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.roles = new ArrayList<>();
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public String getRealName() {
        return realName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public static User validate(Context context, String inputUsername, String inputPassword) {
        AssetManager am = context.getAssets();

        try {
            InputStream is = am.open("users.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");

                String username = tokens[0];
                String password = tokens[1];
                String realName = tokens[2];

                if (inputUsername.equals(username) && inputPassword.equals(password)) {
                    User user = new User(username, password, realName);

                    // Add roles (everything after the third field)
                    for (int i = 3; i < tokens.length; i++) {
                        user.addRole(new Role(tokens[i].trim()));
                    }

                    return user; // success
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // login failed
    }
}
