package com.example.lab5_ygv729.model;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
                // Step 1: Split at colon (between title and roles)
                String[] parts = line.split(":", 2);
                if (parts.length < 2) continue;

                // Step 2: Handle scene number and title from the first part
                String header = parts[0].trim();  // e.g., 1 - "Alexander Hamilton"
                String[] headerParts = header.split(" - ", 2);
                if (headerParts.length < 2) continue;

                int sceneNum = Integer.parseInt(headerParts[0].trim());
                String title = headerParts[1].replace("\"", "").trim();

                // Step 3: Handle roles from the second part
                String[] roleStrings = parts[1].split(",");
                Scene scene = new Scene(sceneNum, title);
                for (String role : roleStrings) {
                    scene.addRole(new Role(role.trim()));
                }

                scenes.add(scene);
                Log.d("SCENE_DEBUG", "Loaded Scene: " + sceneNum + " - " + title + " with roles: " + Arrays.toString(roleStrings));

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
