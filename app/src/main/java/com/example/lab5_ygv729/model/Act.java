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
                String[] parts = line.split(",");
                int sceneNum = Integer.parseInt(parts[0]);
                String title = parts[1].replace("\"", "");
                Scene scene = new Scene(sceneNum, title);
                for (int i = 2; i < parts.length; i++) {
                    scene.addRole(new Role(parts[i]));
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
