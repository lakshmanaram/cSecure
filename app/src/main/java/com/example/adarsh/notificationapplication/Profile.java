package com.example.adarsh.notificationapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //TODO: Receive JSON and update the received_user object
        RecyclerView rv = (RecyclerView) findViewById(R.id.rvContacts);
        User received_user = new User("laksh","lakshmanaram","A102345678","+919000000000","b15 sri harsh19","Adarsh Srivatsan Harshitha","+919342444466 +919988123456 +919234123645");
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
        String[] cnames = received_user.contactsname.split(" ");
        String[] cphone = received_user.contactsphone.split(" ");
        ArrayList<Contact> clist = new ArrayList<>();
        Contact c1 = new Contact(cnames[0], cphone[0], "1");
        clist.add(c1);
        Contact c2 = new Contact(cnames[1], cphone[1], "1");
        clist.add(c2);
        Contact c3 = new Contact(cnames[2], cphone[2], "1");
        clist.add(c3);
        //TODO: list
        ContactsAdapter adapter = new ContactsAdapter(getApplicationContext(), clist);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}
