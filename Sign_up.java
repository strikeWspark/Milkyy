package com.dryfire.milkyy.Activities;

import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.dryfire.milkyy.R;
import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Sign_up extends AppCompatActivity {

    private Spinner spinnerHostel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mky_sign_up);
       spinnerHostel = (Spinner) findViewById(R.id.mky_spinner_hostel);

        String []hostelsnames = {"Boys Hostel - 1","Boys Hostel  - 2","Boys Hostel - 3","Boys Hostel - 4","Boys Hostel - 5","Boys Hostel - 6","Boys Hostel - 7","Boys Hostel - 8",
                "Girls Hostel - 1","Girls Hostel - 2","Girls Hostel - 3","Girls Hostel - 4","Girls Hostel - 5","Girls Hostel - 6"};

       final ArrayList<String> list_of_hostels = new ArrayList<String>(Arrays.asList(hostelsnames));
        ArrayAdapter<String> hostelsAdapter = new ArrayAdapter<String>(this,R.layout.spinner_list_view,R.id.mky_spinner_textview,list_of_hostels);

        spinnerHostel.setAdapter(hostelsAdapter);

        spinnerHostel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Sign_up.this, "You've selected" + list_of_hostels.get(position) , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



}
