package com.example.lab5_ygv729;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab5_ygv729.model.User;

public class RoleActivity extends AppCompatActivity {

    private User currentUser;
    private TextView nameTextView;
    private TextView rolesTextView;
    private Button logoutButton, act1Button, act2Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        nameTextView = findViewById(R.id.nameTextView);
        rolesTextView = findViewById(R.id.rolesTextView);
        logoutButton = findViewById(R.id.logoutButton);
        act1Button = findViewById(R.id.act1Button);
        act2Button = findViewById(R.id.act2Button);

        currentUser = (User) getIntent().getSerializableExtra("user");

        if (currentUser != null) {
            nameTextView.setText("Welcome, " + currentUser.getRealName());

            StringBuilder roleList = new StringBuilder();
            for (int i = 0; i < currentUser.getRoles().size(); i++) {
                roleList.append(currentUser.getRoles().get(i).getName()).append("\n");
            }
            rolesTextView.setText(roleList.toString());
        } else {
            Toast.makeText(this, "No user data found", Toast.LENGTH_SHORT).show();
            finish();
        }

        logoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(RoleActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        act1Button.setOnClickListener(v -> openActView(1));
        act2Button.setOnClickListener(v -> openActView(2));
    }

    private void openActView(int actNumber) {
        Intent intent = new Intent(RoleActivity.this, ActActivity.class);
        intent.putExtra("user", currentUser);
        intent.putExtra("actNumber", actNumber);
        startActivity(intent);
    }
}
