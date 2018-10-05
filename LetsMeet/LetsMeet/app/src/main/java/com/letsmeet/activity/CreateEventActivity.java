package com.letsmeet.activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.letsmeet.R;
import com.letsmeet.entity.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateEventActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Button btnCreate;
    EditText eventName, eventDesc, editDate, eventCategory, eventLocation, capacity;
    Event event = null;
    PlaceAutocompleteFragment autocompleteFragment ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        editDate = findViewById(R.id.txtDate);
        eventName = findViewById(R.id.txtEventName);
        eventDesc = findViewById(R.id.txtEventDescription);
        eventCategory = findViewById(R.id.txtEventCategory);
        //eventLocation = findViewById(R.id.txtEventLocation);
        capacity = findViewById(R.id.txtCapacity);
        btnCreate = findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date = null;
                try {
                        event = new Event();
                        event.setEventName(eventName.getText().toString());
                        event.setEventDescription(eventDesc.getText().toString());
                        date = dateFormat.parse(editDate.getText().toString());
                        event.setEventDate(date);
                        event.setCapacity(Integer.parseInt(capacity.getText().toString()));
                        event.setCategory(eventCategory.getText().toString());
                        event.setLocation(eventLocation.getText().toString());
                        event.setEventCreationDate(dateFormat.parse(dateFormat.format(new Date())));
                        persistObjectInFireBase(event);
                } catch (ParseException e) {
                    e.printStackTrace();
                }catch(Exception e2){
                   Log.v("Error - ", e2.toString());
                }
                clearFields();
            }

            private void clearFields() {
                editDate.setText("");
                eventName.setText("");
                eventDesc.setText("");
                eventCategory.setText("");
                autocompleteFragment.setText("");
                capacity.setText("");
            }
        });
        Toast.makeText(this, "Event Created Successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.txtEventLocation);
        autocompleteFragment.setHint(getString(R.string.location));
        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                .build();
        autocompleteFragment.setFilter(typeFilter);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                System.out.println( "Place: " + place.getName());//get place details here
            }
            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                System.out.println("An error occurred: " + status);
            }
        });
    }

    private void persistObjectInFireBase(Event event) {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("events");
        databaseReference.push();
        databaseReference.setValue(event);

    }
}
