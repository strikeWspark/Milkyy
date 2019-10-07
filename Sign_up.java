package com.dryfire.milkyy.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dryfire.milkyy.Milkyy.Milkyy;
import com.dryfire.milkyy.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    private TextInputLayout password_input;
    private TextInputEditText password_edit;
    private ProgressDialog mprogressSignup;
    private AlertDialog.Builder signupdialogbuilder,termsbuilder;
    private Dialog signupdialog,termsdialog;
    private MaterialButton submit;

    private String h;

    DatabaseReference databaseReference;
    Milkyy milkyy;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mky_sign_up);


        mprogressSignup = new ProgressDialog(this);
        name_input = (TextInputLayout) findViewById(R.id.mky_name_input);
        name_edit = (TextInputEditText) findViewById(R.id.mky_name_edit);

        regd_input = (TextInputLayout) findViewById(R.id.mky_regdno_input);
        regd_edit = (TextInputEditText) findViewById(R.id.mky_regdno_edit);

        mobile_input = (TextInputLayout) findViewById(R.id.mky_mobileno_input);
        mobile_edit = (TextInputEditText) findViewById(R.id.mky_mobileno_edit);

        password_input = (TextInputLayout) findViewById(R.id.mky_signup_password_input);
        password_edit = (TextInputEditText) findViewById(R.id.mky_signUp_password_edit);

        spinnerHostel = (Spinner) findViewById(R.id.mky_spinner_hostel);

        submit=findViewById(R.id.mky_signUp_submit);

        signupdialogbuilder = new AlertDialog.Builder(this);
        termsbuilder = new AlertDialog.Builder(this);

        Toolbar sign_toolbar = (Toolbar) findViewById(R.id.mky_signup_toolbar);
        setSupportActionBar(sign_toolbar);

        sign_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sign_up.this,MainActivity.class));
                finish();
            }
        });

        String []hostelsnames = {"Boys Hostel - 1","Boys Hostel - 2","Boys Hostel - 3","Boys Hostel - 4","Boys Hostel - 5","Boys Hostel - 6","Boys Hostel - 7","Boys Hostel - 8",
                "Girls Hostel - 1","Girls Hostel - 2","Girls Hostel - 3","Girls Hostel - 4","Girls Hostel - 5","Girls Hostel - 6","Block - 41A",
                "Block - 42B","Block - 43C","Block - 44D"};

       final ArrayList<String> list_of_hostels = new ArrayList<String>(Arrays.asList(hostelsnames));
        ArrayAdapter<String> hostelsAdapter = new ArrayAdapter<String>(this,R.layout.mky_spinner_list_view,list_of_hostels);

        spinnerHostel.setAdapter(hostelsAdapter);

        spinnerHostel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Sign_up.this, "You've selected" + list_of_hostels.get(position) , Toast.LENGTH_SHORT).show();
                h=list_of_hostels.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        milkyy=new Milkyy();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Milkyy");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mprogressSignup.setMessage("Signing Up...");
                mprogressSignup.show();
                send();
                }
        });
    }

    private void send() {
        milkyy.setReg(regd_edit.getText().toString().trim());
        //System.out.print(regd_edit.getText().toString().trim());
        milkyy.setName(name_edit.getText().toString().trim());
        milkyy.setbalance("600");
        milkyy.setQuantity("10");
        String pass=password_edit.getText().toString();
        milkyy.setPassword(password_edit.getText().toString().trim());
        milkyy.setMobile(mobile_edit.getText().toString().trim());
        milkyy.setTrxnid("sdsh2555");
        milkyy.setHostel(h);
        databaseReference.child(milkyy.getReg()).setValue(milkyy);


        Intent intent=new Intent(Sign_up.this,HomeLayout.class);
        intent.putExtra("reg",regd_edit.getText().toString().trim());
        startActivity(intent);
        mprogressSignup.dismiss();
        finish();
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
                signaboutus.setText("Free pure milk delivery in LPU at your hostel gates. With Milkyy, you can fulfil all your dialy essential quality milk needs");
                signupdialogbuilder.setView(aboutusview);
                signupdialog = signupdialogbuilder.create();
                signupdialog.show();
                Toast.makeText(this, "About Us clicked", Toast.LENGTH_SHORT).show();

                break;
            case R.id.mky_terms_condition:
                View terms_view = getLayoutInflater().inflate(R.layout.mky_terms,null);
                TextView terms_and_condition = (TextView) terms_view.findViewById(R.id.mky_terms_textView);
                terms_and_condition.setText("* If the free glass that we provide is broken or lost, then we'll charge if you need a new one.\n"
                + "\n* In the given time, you have to reach and get your milk, if you will miss then it is not our responsibility, then that day it will cut out from your subscription box.\n " +
                        "\n * If you do not want milk at any particular day then you'll have to give us a message before delivery and then it will not cut out from your subscription box.");
                termsbuilder.setView(terms_view);
                termsdialog = termsbuilder.create();
                termsdialog.show();
                    break;
        }
        return super.onOptionsItemSelected(item);
    }
}
