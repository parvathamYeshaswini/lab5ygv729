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

        for (Scene scene : act.getScenes()) {
            for (Role sceneRole : scene.getRoles()) {
                for (Role userRole : currentUser.getRoles()) {
                    Log.d("MATCH_CHECK", "User role: '" + userRole.getName() + "' vs Scene role: '" + sceneRole.getName() + "'");

                    if (sceneRole.getName().equalsIgnoreCase(userRole.getName())) {
                        userScenes.add(scene);
                        break;
                    }
                }
            }
        }

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
