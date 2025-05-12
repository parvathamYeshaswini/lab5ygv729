package com.example.lab5_ygv729.model;

import android.content.Context;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Act {
    private int number;
    private ArrayList<Scene> scenes;

    public Act(int number) {
        this.number = number;
        this.scenes = new ArrayList<>();
    }

    public void loadScenesFromFile(Context context, String fileName) {
        try {
            InputStream is = context.getAssets().open(fileName);
            Scanner scan = new Scanner(is);

            while (scan.hasNextLine()) {
                String temp = scan.nextLine().trim();

                // Split by colon (:) to separate title from roles
                String[] sceneSplit = temp.split(":", 2);
                if (sceneSplit.length < 2) continue;

                // Left of colon: e.g., 1 - "Alexander Hamilton"
                String[] leftSplit = sceneSplit[0].split("-", 2);
                if (leftSplit.length < 2) continue;

                int sceneId = Integer.parseInt(leftSplit[0].trim());
                String title = leftSplit[1].replace("\"", "").trim();

                // Right of colon: list of roles
                String[] rolesArray = sceneSplit[1].split(",");
                ArrayList<Role> roleList = new ArrayList<>();
                for (String role : rolesArray) {
                    roleList.add(new Role(role.trim()));
                }

                Scene scene = new Scene(sceneId, title);
                for (Role role : roleList) {
                    scene.addRole(role);
                }

                scenes.add(scene);
            }

            scan.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Scene> getScenes() {
        return scenes;
    }

    public int getNumber() {
        return number;
    }
}
