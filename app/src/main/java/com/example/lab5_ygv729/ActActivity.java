package com.example.lab5_ygv729;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab5_ygv729.model.Act;
import com.example.lab5_ygv729.model.Role;
import com.example.lab5_ygv729.model.Scene;
import com.example.lab5_ygv729.model.User;

import java.util.ArrayList;
import java.util.List;

public class ActActivity extends AppCompatActivity {

    private TextView sceneListTextView;
    private User currentUser;
    private int actNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act);

        sceneListTextView = findViewById(R.id.sceneListTextView);

        Intent intent = getIntent();
        currentUser = (User) intent.getSerializableExtra("user");
        actNumber = intent.getIntExtra("actNumber", 1);

        loadAndDisplayScenes();
    }

    private void loadAndDisplayScenes() {
        String fileName = (actNumber == 1) ? "act1.txt" : "act2.txt";
        Act act = new Act(actNumber);
        act.loadScenesFromFile(this, fileName);

        List<Scene> userScenes = new ArrayList<>();

        // Debug: Print user roles
        for (Role r : currentUser.getRoles()) {
            Log.d("DEBUG_USER_ROLE", "User has role: '" + r.getName() + "'");
        }

        // Debug: Print scene roles
        for (Scene s : act.getScenes()) {
            Log.d("DEBUG_SCENE", "Scene " + s.getNumber() + ": " + s.getTitle());
            for (Role r : s.getRoles()) {
                Log.d("DEBUG_SCENE_ROLE", " - Role in scene: '" + r.getName() + "'");
            }
        }

        // Main logic: Match user roles with scene roles
        for (Scene scene : act.getScenes()) {
            for (Role sceneRole : scene.getRoles()) {
                for (Role userRole : currentUser.getRoles()) {
                    // Clean names: remove spaces, punctuation, make lowercase
                    String sceneRoleName = sceneRole.getName().trim().toLowerCase();
                    String userRoleName = userRole.getName().trim().toLowerCase();

                    Log.d("ROLE_MATCH_DEBUG", "Comparing scene role '" + sceneRoleName + "' with user role '" + userRoleName + "'");

                    if (sceneRoleName.equals(userRoleName)) {
                        if (!userScenes.contains(scene)) {
                            userScenes.add(scene);
                            Log.d("MATCH_FOUND", "✅ MATCH FOUND: Scene " + scene.getNumber() + " for role '" + userRoleName + "'");
                        }
                    }
                }
            }
        }

        // Display matched scenes
        if (userScenes.isEmpty()) {
            sceneListTextView.setText("no scenes");
        } else {
            StringBuilder result = new StringBuilder();
            for (Scene s : userScenes) {
                result.append("Scene ").append(s.getNumber()).append(": ").append(s.getTitle()).append("\n");
            }
            sceneListTextView.setText(result.toString());
        }
    }
}
