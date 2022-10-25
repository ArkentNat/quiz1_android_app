package com.example.quiz1_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPageActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private TextView signupBtn;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        signupBtn = findViewById(R.id.signup_text_link);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginPageActivity.this, RegisterPageActivity.class));

            }
        });

        loginBtn = findViewById(R.id.login_button);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameStr = username.getText().toString();
                String passwordStr = password.getText().toString();

                if(usernameStr.equals("Admin") && passwordStr.equals("12345678")) {
                    Intent intent = new Intent(view.getContext(), UserListViewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("username", "admin");
                    intent.putExtras(bundle);
                    username.setText("");
                    password.setText("");
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginPageActivity.this, "Wrong username or password!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}