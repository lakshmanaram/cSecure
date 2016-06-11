package com.example.adarsh.notificationapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //TODO: Receive JSON and update the received_user object
        User received_user = new User("laksh","lakshmanaram","A102345678","+919000000000","b15 sri harsh19","Adarsh Srivatsan Harshitha");
        TextView uname = (TextView)findViewById(R.id.profileusername);
        assert uname!=null;
        uname.setText(received_user.username);
        TextView name = (TextView)findViewById(R.id.profilename);
        assert name!=null;
        name.setText(received_user.name);
        TextView aadharno = (TextView)findViewById(R.id.profileaadharno);
        assert aadharno!=null;
        aadharno.setText(received_user.aadharno);
        TextView phone = (TextView)findViewById(R.id.profilephone);
        assert phone!=null;
        phone.setText(received_user.phone);
        //TODO: list
    }
}
