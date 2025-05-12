package com.example.lab5_ygv729.model;

import android.content.Context;
import android.util.Log;

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
                // First split only on first 3 commas to get username, password, realName
                String[] firstParts = line.split(",", 4); // limit = 4
                if (firstParts.length < 3) continue;

                String username = firstParts[0].trim();
                String password = firstParts[1].trim();
                String realName = firstParts[2].trim();

                if (inputUsername.equals(username) && inputPassword.equals(password)) {
                    List<Role> roles = new ArrayList<>();

                    if (firstParts.length == 4) {
                        // Now split remaining roles safely
                        String[] roleParts = firstParts[3].split(",");
                        for (String role : roleParts) {
                            if (!role.trim().isEmpty()) {
                                // Remove anything in parentheses (e.g., "Alexander Hamilton (Hamilton)" => "Hamilton")
                                String cleaned = role.replaceAll(".*\\((.*?)\\)", "$1").trim();
                                roles.add(new Role(cleaned));
                            }
                        }
                    }

                    return new User(username, password, realName, roles);
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
