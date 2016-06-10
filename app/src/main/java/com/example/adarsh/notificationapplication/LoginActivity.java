package com.example.adarsh.notificationapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    final static String UNAME = "username";
    final static String PASS = "password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences prefs = getSharedPreferences("user",Context.MODE_PRIVATE);
        String username = prefs.getString(UNAME, "default");
        if(!username.equals("default")){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
        Button login = (Button) findViewById(R.id.login);
        assert login != null;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = "",password = "";
                SharedPreferences share = getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = share.edit();
                editor.putString(UNAME, username);
                editor.putString(PASS, password);
                editor.apply();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        Button signup = (Button) findViewById(R.id.signup);
        assert signup != null;
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getApplicationContext(),SignUp.class));
            }
        });
    }
}
