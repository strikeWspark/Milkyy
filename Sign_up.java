package com.dryfire.milkyy.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dryfire.milkyy.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Sign_up extends AppCompatActivity {

    private Spinner spinnerHostel;
    private TextInputLayout name_input;
    private TextInputEditText name_edit;
    private TextInputLayout regd_input;
    private TextInputEditText regd_edit;
    private TextInputLayout mobile_input;
    private TextInputEditText mobile_edit;
    private AlertDialog.Builder signupdialogbuilder;
    private Dialog signupdialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mky_sign_up);

        name_input = (TextInputLayout) findViewById(R.id.mky_name_input);
        name_edit = (TextInputEditText) findViewById(R.id.mky_name_edit);

        regd_input = (TextInputLayout) findViewById(R.id.mky_regdno_input);
        regd_edit = (TextInputEditText) findViewById(R.id.mky_regdno_edit);

        mobile_input = (TextInputLayout) findViewById(R.id.mky_mobileno_input);
        mobile_edit = (TextInputEditText) findViewById(R.id.mky_mobileno_edit);

        spinnerHostel = (Spinner) findViewById(R.id.mky_spinner_hostel);

        signupdialogbuilder = new AlertDialog.Builder(this);

        Toolbar sign_toolbar = (Toolbar) findViewById(R.id.mky_signup_toolbar);
        setSupportActionBar(sign_toolbar);

        sign_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sign_up.this,MainActivity.class));
                finish();
            }
        });

        String []hostelsnames = {"Boys Hostel - 1","Boys Hostel  - 2","Boys Hostel - 3","Boys Hostel - 4","Boys Hostel - 5","Boys Hostel - 6","Boys Hostel - 7","Boys Hostel - 8",
                "Girls Hostel - 1","Girls Hostel - 2","Girls Hostel - 3","Girls Hostel - 4","Girls Hostel - 5","Girls Hostel - 6"};

       final ArrayList<String> list_of_hostels = new ArrayList<String>(Arrays.asList(hostelsnames));
        ArrayAdapter<String> hostelsAdapter = new ArrayAdapter<String>(this,R.layout.mky_spinner_list_view,list_of_hostels);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mky_sign_toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.mky_sign_about_us:
                View aboutusview = getLayoutInflater().inflate(R.layout.about_us,null);
                TextView signaboutus = (TextView) aboutusview.findViewById(R.id.mky_about_us_textView);
                signaboutus.setText("Heap - \n" +
                        "\tA heap is a speacial Tree-based data structure in which the tree is a complete binary tree. Generally Heaps can be of two types :\n" +
                        "a) Min Heap\n" +
                        "b) Max Heap\n" +
                        "\n" +
                        "Min Heap - \n" +
                        "In a Mix Heap the key present at the root node must be minimum among the keys present at all of it's chlidren. The same proeprty must be recursively true for all sub-trees in that binary tree.\n" +
                        "\n" +
                        "Max Heap - \n" +
                        "In a Ma Heap the key present at the root node must be maximum among the keys present at all of it's children. The same property must be recursively true for all sub-trees in that binary tree.\n" +
                        "\n" +
                        "\n" +
                        "Min Heap\t\n" +
                        "\t\t\t\t1\n" +
                        "\t\t\t       / \\\n" +
                        "\t\t\t      3   4\n" +
                        "\t\t\t     / \\  / \\\n" +
                        "\t\t\t    5  6 7   8\n" +
                        "\n" +
                        "Max Heap\n" +
                        "\t\t\t\t8\n" +
                        "\t\t\t       / \\\n" +
                        "\t\t\t      7\t  6\n" +
                        "\t\t\t     / \\  / \n" +
                        "\t\t\t    3   2 1");
                signupdialogbuilder.setView(aboutusview);
                signupdialog = signupdialogbuilder.create();
                signupdialog.show();
                Toast.makeText(this, "About Us clicked", Toast.LENGTH_SHORT).show();

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
