package com.letsmeet.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.letsmeet.R;

public class ProfileSettings extends AppCompatActivity {

    private Button btnEditSave;
    private Button btnChangeImage;
    private EditText editFirstNameText;
    private EditText editLastNameText;

    private boolean isInEdit = true;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        btnEditSave = findViewById(R.id.btnEditSave);
        editFirstNameText = findViewById(R.id.firstname);
        editFirstNameText.setEnabled(false);
        editLastNameText = findViewById(R.id.lastname);
        editLastNameText.setEnabled(false);
        btnChangeImage = findViewById(R.id.btnChangeImage);


        btnEditSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isInEdit) {
                    //btnEditSave.
                    String buttonText = btnEditSave.getText().toString();
                    editFirstNameText.setText(buttonText);
                    editFirstNameText.setEnabled(true);
                    editLastNameText.setEnabled(true);
                    btnChangeImage.setVisibility(View.VISIBLE);
                }
            }
        });
    }




}
