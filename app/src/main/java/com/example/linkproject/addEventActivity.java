package com.example.linkproject;
/*
  addEventActivity.java
  Luke Mason & JD Gruber
  CPSC 312 Final Project
  Link
  Creates an event with defined parameters in edit texts
  and then passes the information back to maps
*/
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addEventActivity extends AppCompatActivity {

    static final String TAG = "addMarkerActivity";
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mEventsDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_marker);
        setupFirebase();
    }

    private void setupFirebase() {
        // initialize the firebase references
        mFirebaseDatabase =
                FirebaseDatabase.getInstance();
        mEventsDatabaseReference =
                mFirebaseDatabase.getReference()
                        .child("events");
    }

    public void onDoneButtonClick(View view) {
        EditText titleET = findViewById(R.id.editText3);
        EditText descriptionET = findViewById(R.id.editText4);
        EditText dateET = findViewById(R.id.editText);
        EditText startET = findViewById(R.id.editText2);
        EditText endET = findViewById(R.id.editText5);
        EditText locationET = findViewById(R.id.editText6);
        String title = titleET.getText().toString();
        String description = descriptionET.getText().toString();
        String date = dateET.getText().toString();
        String start = startET.getText().toString();
        String end = endET.getText().toString();
        String location = locationET.getText().toString();

        if (location.length() > 0 && description.length() > 0 && title.length() > 0 &&
            date.length() > 0 && start.length() > 0 && end.length() > 0) {
            Event event = new Event(title, description, location, date, start, end);
            mEventsDatabaseReference
                    .push()
                    .setValue(event);
            finish();
        }
        else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_LONG).show();
        }
    }
}
