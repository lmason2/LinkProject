package com.example.linkproject;

/*
  EventList.java
  Luke Mason & JD Gruber
  CPSC 312 Final Project
  Link
  Class to show the events in the database
  as a list view
*/

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EventList extends AppCompatActivity {
    static final String TAG = "EventList";
    static final int LOGIN_REQUEST_CODE = 1;

    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<Event> list;
    ArrayAdapter<Event> adapter;
    Event event;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        event = new Event();
        listView = (ListView) findViewById(R.id.linkEvents);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("events");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<Event>(this, android.R.layout.simple_list_item_1, list);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    event = ds.getValue(Event.class);
                    list.add(event);
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event curEvent = list.get(position);
                Intent intent = new Intent(EventList.this, ShowEvent.class);
                intent.putExtra("Title", curEvent.getTitle());
                intent.putExtra("Location", curEvent.getLocation());
                intent.putExtra("Start", curEvent.getStart());
                intent.putExtra("End", curEvent.getEnd());
                intent.putExtra("Description", curEvent.getDescription());
                intent.putExtra("Date", curEvent.getDate());
                startActivityForResult(intent, LOGIN_REQUEST_CODE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.events_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.aboutMenuItem) {
            return true;
        } else if (id == R.id.addMenuItem) {
            Intent intent = new Intent(EventList.this, addEventActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}