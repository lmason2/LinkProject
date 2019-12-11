package com.example.linkproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

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
            title.setText(intent.getStringExtra("Title"));
            location.setText(intent.getStringExtra("Location"));
            description.setText(intent.getStringExtra("Description"));
            date.setText(intent.getStringExtra("Date"));
            start.setText(intent.getStringExtra("Start"));
            end.setText(intent.getStringExtra("End"));
        }
    }
}
