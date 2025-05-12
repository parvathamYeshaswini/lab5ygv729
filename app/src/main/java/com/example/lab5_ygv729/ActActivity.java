package com.example.lab5_ygv729;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActActivity extends AppCompatActivity {

    private TextView sceneList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act);

        sceneList = findViewById(R.id.sceneList);

        int actNumber = getIntent().getIntExtra("actNumber", 1);
        String roleString = getIntent().getStringExtra("roles");

        List<String> userRoles = Arrays.asList(roleString.split("\n"));

        try {
            List<String> relevantScenes = loadScenesForRoles(actNumber, userRoles);
            if (relevantScenes.isEmpty()) {
                sceneList.setText("No scenes");
            } else {
                StringBuilder sb = new StringBuilder();
                for (String scene : relevantScenes) {
                    sb.append(scene).append("\n\n");
                }
                sceneList.setText(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            sceneList.setText("Error loading scenes");
        }
    }

    private List<String> loadScenesForRoles(int actNumber, List<String> userRoles) throws IOException {
        List<String> scenes = new ArrayList<>();
        AssetManager assetManager = getAssets();

        String filename = (actNumber == 1) ? "act1.txt" : "act2.txt";

        InputStream is = assetManager.open(filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("–|–|:"); // split on dash or colon
            if (parts.length < 2) continue;

            String sceneText = line.trim();
            String characterList = line.substring(line.indexOf(":") + 1);

            for (String role : userRoles) {
                if (characterList.toLowerCase().contains(role.toLowerCase())) {
                    scenes.add(sceneText);
                    break; // only add scene once if any role matches
                }
            }
        }

        return scenes;
    }
}