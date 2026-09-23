package com.example.securobot;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etUser;
    EditText etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUser = findViewById(R.id.etUser);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String user = etUser.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

           if (!user.isEmpty() && !password.isEmpty()) {

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("username", user);
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(
                        LoginActivity.this,
                        getString(R.string.login_error),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}