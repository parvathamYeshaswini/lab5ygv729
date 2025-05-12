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
                // Step 1: Split the line at any dash (en, em, or hyphen)
                String[] parts = line.split("[–—-]", 2); // limit = 2 to avoid over-splitting
                if (parts.length < 2) continue;

                // Step 2: Extract scene number and title
                String header = parts[0].trim(); // e.g., 1
                String sceneAndRoles = parts[1].trim(); // e.g., "Title" – Role1, Role2

                // Extract scene title
                int quoteStart = sceneAndRoles.indexOf("\"");
                int quoteEnd = sceneAndRoles.indexOf("\"", quoteStart + 1);
                if (quoteStart == -1 || quoteEnd == -1) continue;

                String title = sceneAndRoles.substring(quoteStart + 1, quoteEnd).trim();
                String roleList = sceneAndRoles.substring(quoteEnd + 1).trim();

                int sceneNum = Integer.parseInt(header);

                Scene scene = new Scene(sceneNum, title);

                String[] roleStrings = roleList.split(",");
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
