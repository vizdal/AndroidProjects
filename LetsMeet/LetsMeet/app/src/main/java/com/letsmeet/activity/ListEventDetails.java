package com.letsmeet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.letsmeet.R;
import com.letsmeet.entity.Event;

public class ListEventDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_event_details);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("events");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Event event = dataSnapshot.getValue(Event.class);
                System.out.println(event);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
 //       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton addEvent = findViewById(R.id.addEventButton);
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent addEventIntent = new Intent(getBaseContext(),CreateEventActivity.class);
            startActivity(addEventIntent);
            }
        });

        FloatingActionButton profileSetUpEvent = findViewById(R.id.btnProfileSetUp);
        profileSetUpEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ProfileSettingsIntent = new Intent(getBaseContext(),ProfileSettings.class);
                startActivity(ProfileSettingsIntent);
            }
        });
    }

}
