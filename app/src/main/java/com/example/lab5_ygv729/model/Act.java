package com.example.lab5_ygv729.model;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Act {
    private int number;
    private List<Scene> scenes;

    public Act(int number) {
        this.number = number;
        this.scenes = new ArrayList<>();
    }

    public void loadScenesFromFile(Context context, String fileName) {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(fileName))
            );
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length < 2) continue;

                String header = parts[0].trim();
                String[] headerParts = header.split(" - ", 2);
                if (headerParts.length < 2) continue;

                int sceneNum = Integer.parseInt(headerParts[0].trim());
                String title = headerParts[1].replace("\"", "").trim();

                Scene scene = new Scene(sceneNum, title);

                String[] roleStrings = parts[1].split(",");
                for (String role : roleStrings) {
                    scene.addRole(new Role(role.trim()));
                }

                scenes.add(scene);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Scene> getScenes() {
        return scenes;
    }

    public int getNumber() {
        return number;
    }
}
