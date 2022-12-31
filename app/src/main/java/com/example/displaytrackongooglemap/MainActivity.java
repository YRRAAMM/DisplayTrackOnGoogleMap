package com.example.displaytrackongooglemap;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/*
* this application
* if installed
* display the start point and end point in a google maps
* if not it goes to google play to download it
* */


public class MainActivity extends AppCompatActivity {

    EditText mSource_edt, mDestination_edt;
    Button mDisplay_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSource_edt = findViewById(R.id.source_edt);
        mDestination_edt = findViewById(R.id.destination_edt);
        mDisplay_btn = findViewById(R.id.display_btn);

        mDisplay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get value from edit text
                String source_txt = mSource_edt.getText().toString().trim();
                String destination_txt = mDestination_edt.getText().toString().trim();

                if (source_txt.equals("") && destination_txt.equals("")) {
                    Toast.makeText(MainActivity.this, "Enter both location", Toast.LENGTH_SHORT).show();
                } else {
                    // When both value fill
                    // Display track
                    DisplayTrack(source_txt, destination_txt);
                }
            }
        });

    }

    private void DisplayTrack(String source, String destination) {
        // If the device does not have a map installed, then redirect it to play store
        try {
            // When google map is installed
            // Initialize uri
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + source + "/"
            + destination);
            // Initialize intent with action view
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            // set package
            intent.setPackage("com.google.android.apps.maps");
            // set flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // Start activity
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // When google map is not installed
            // Inititlaize uri
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            // initialize intent with action view
            Intent intent = new Intent(Intent.ACTION_VIEW);
            // Set flag
            getIntent().setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // Start activity
            startActivity(intent);
        }
    }
}