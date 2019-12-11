package com.example.linkproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_event);

        TextView title = findViewById(R.id.Title);
        TextView location = findViewById(R.id.Location);
        TextView description = findViewById(R.id.Description);
        TextView date = findViewById(R.id.Date);
        TextView start = findViewById(R.id.Start);
        TextView end = findViewById(R.id.End);

        Intent intent = getIntent();
        if (intent != null) {
            String locationString = getString(R.string.location) + " " + intent.getStringExtra("Location");
            String descriptionString = getString(R.string.description) + " " + intent.getStringExtra("Description");
            String dateString = getString(R.string.date) + " " + intent.getStringExtra("Date");
            String startString = getString(R.string.start) + " " + intent.getStringExtra("Start");
            String endString = getString(R.string.end) + " " + git aintent.getStringExtra("End");
            title.setText(intent.getStringExtra("Title"));
            location.setText(locationString);
            description.setText(descriptionString);
            date.setText(dateString);
            start.setText(startString);
            end.setText(endString);
        }
    }
}
