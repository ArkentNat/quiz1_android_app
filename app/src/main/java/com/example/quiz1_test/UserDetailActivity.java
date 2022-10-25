package com.example.quiz1_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.quiz1_test.Model.User;

import org.json.JSONException;
import org.json.JSONObject;

public class UserDetailActivity extends AppCompatActivity {

    private TextView id;
    private ImageView profilePic;
    private TextView fname;
    private TextView lname;
    private TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        id = findViewById(R.id.id_text);
        profilePic = findViewById(R.id.profilePic);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.user_email);


        User user = (User)getIntent().getSerializableExtra("userDetail");
        Log.d("DEBUG", user.getFirstname());


        Glide.with(this)
                .load(user.getAvatar())
                .into(profilePic);

        id.setText(String.valueOf(user.getId()));
        fname.setText(user.getFirstname());
        lname.setText(user.getLastname());
        email.setText(user.getEmail());




    }
}