package com.example.lab5_ygv729;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RoleActivity extends AppCompatActivity {

    private TextView realNameText;
    private TextView roleListText;
    private Button act1Button, act2Button, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        realNameText = findViewById(R.id.realName);
        roleListText = findViewById(R.id.roleList);
        act1Button = findViewById(R.id.act1Button);
        act2Button = findViewById(R.id.act2Button);
        logoutButton = findViewById(R.id.logoutButton);

        // Receive data from MainActivity
        String realName = getIntent().getStringExtra("realName");
        String roles = getIntent().getStringExtra("roles");

        realNameText.setText("Name: " + realName);
        roleListText.setText("Role(s):\n" + roles);

        act1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActActivity(1);
            }
        });

        act2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActActivity(2);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to MainActivity
            }
        });
    }

    private void openActActivity(int actNumber) {
        Intent intent = new Intent(RoleActivity.this, ActActivity.class);
        intent.putExtra("actNumber", actNumber);
        intent.putExtra("roles", getIntent().getStringExtra("roles"));
        startActivity(intent);
    }
}
