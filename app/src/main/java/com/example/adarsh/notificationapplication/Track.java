package com.example.adarsh.notificationapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class Track extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rvContacts);

        ArrayList<Contact> clist = new ArrayList<>();
        Contact c1 = new Contact("Adarsh", "+91-9000000000", "2");
        clist.add(c1);
        Contact c2 = new Contact("Srivatsan", "+91-9000000001", "2");
        clist.add(c2);
        Contact c3 = new Contact("Harshitha", "+91-9000000002", "2");
        clist.add(c3);
        //TODO: list
        ContactsAdapter adapter = new ContactsAdapter(getApplicationContext(), clist);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}
