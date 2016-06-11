package com.example.adarsh.notificationapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class CautionContacts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caution_contacts);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rvContacts);

        ArrayList<Contact> clist = new ArrayList<>();
        Contact c1 = new Contact("Adarsh", "+919043804101", "3");
        clist.add(c1);
        Contact c2 = new Contact("Srivatsan", "+919443267652", "3");
        clist.add(c2);
        Contact c3 = new Contact("Harshitha", "+919000000002", "3");
        clist.add(c3);
        Contact c4 = new Contact("Local Police Office", "+919826443571", "3");
        clist.add(c4);
        Contact c5 = new Contact("Local Hospital", "+919774167571", "3");
        clist.add(c5);
        ContactsAdapter adapter = new ContactsAdapter(getApplicationContext(), clist);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}
