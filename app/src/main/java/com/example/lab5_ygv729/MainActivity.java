package com.example.lab5_ygv729;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;



import androidx.appcompat.app.AppCompatActivity;

import com.example.lab5_ygv729.model.User;
import com.example.lab5_ygv729.model.Role;


public class MainActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // make sure this file exists

        // Find views by ID after setContentView
        usernameInput = findViewById(R.id.username);
        passwordInput = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        // Set up click listener
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                User user = User.validate(getApplicationContext(), username, password);

                if (user != null) {
                    Intent intent = new Intent(MainActivity.this, RoleActivity.class);
                    intent.putExtra("realName", user.getRealName());

// Convert roles to comma-separated string
                    StringBuilder rolesString = new StringBuilder();
                    for (Role role : user.getRoles()) {
                        rolesString.append(role.getName()).append("\n");
                    }
                    intent.putExtra("roles", rolesString.toString());

                    startActivity(intent);

                } else {
                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}