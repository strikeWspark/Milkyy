package com.dryfire.milkyy.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;

import android.view.View;
import android.widget.Toast;


import com.dryfire.milkyy.Milkyy.Milkyy;
import com.dryfire.milkyy.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout usernameInput;
    private TextInputEditText usernameEdit;
    private TextInputLayout passwordInput;
    private TextInputEditText passwordEdit;
    private MaterialButton cancelButton;
    private MaterialButton nextButton;
    private MaterialButton signUpButton;
    private AlertDialog.Builder exitbuilder;
    private Dialog exitdialog;
    private ProgressDialog mprogressdialog;

    DatabaseReference dreff,mreff;
    Milkyy milkyy;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mprogressdialog = new ProgressDialog(this);
        exitbuilder = new AlertDialog.Builder(this);
        usernameInput = (TextInputLayout) findViewById(R.id.mky_username_input);
        usernameEdit = (TextInputEditText) findViewById(R.id.mky_username_edit);
        passwordInput = (TextInputLayout) findViewById(R.id.mky_password_input);
        passwordEdit = (TextInputEditText) findViewById(R.id.mkyy_password_edit);
        cancelButton = (MaterialButton) findViewById(R.id.mky_cancel_button);
        nextButton = (MaterialButton) findViewById(R.id.mky_next_button);
        signUpButton = (MaterialButton) findViewById(R.id.mky_signup_button);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mprogressdialog.setMessage("Signing In...");
                mprogressdialog.show();
                if (!isPasswordValid(passwordEdit.getText())) {
                    passwordInput.setError(getString(R.string.mky_error_password));
                } else {
                    passwordInput.setError(null);

                    System.out.println(usernameEdit.getText().toString().trim());
                    System.out.println(passwordEdit.getText().toString().trim());
                    final Intent intent=new Intent(MainActivity.this,HomeLayout.class);
                    intent.putExtra("reg",usernameEdit.getText().toString().trim());


                    dreff=FirebaseDatabase.getInstance().getReference().child("Milkyy");
                    dreff.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot dsp: dataSnapshot.getChildren()){
                                String m=dsp.getKey();
                                System.out.println("Key from firebase"+m);
                                Log.d("Firebase Values: Key = ",m);
                                mreff=FirebaseDatabase.getInstance().getReference().child("Milkyy").child(m);
                                mreff.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        String retreg=dataSnapshot.child("reg").getValue().toString();
                                        String retpass=dataSnapshot.child("password").getValue().toString();

                                        System.out.println(retreg + ", " + retpass);
                                        if(retreg.equals(usernameEdit.getText().toString().trim()) && retpass.equals(passwordEdit.getText().toString().trim())) {
                                            startActivity(intent);
                                            mprogressdialog.dismiss();
                                            finish();
                                        }
                                        else{
                                            Toast.makeText(MainActivity.this,"Invalid username ad password",Toast.LENGTH_LONG).show();
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

//                    startActivity(new Intent(MainActivity.this, HomeLayout.class));
//                    finish();
                }
            }
        });

        passwordEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (isPasswordValid(passwordEdit.getText())) {
                    passwordInput.setError(null);
                }
                return false;
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View view = getLayoutInflater().inflate(R.layout.close_option,null);
                MaterialButton exit = (MaterialButton) view.findViewById(R.id.mky_exit_button);
                MaterialButton cancel = (MaterialButton) view.findViewById(R.id.mky_cancel);

                exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                         finish();
                         System.exit(0);
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitdialog.dismiss();
                    }
                });

                exitbuilder.setView(view);
                exitdialog = exitbuilder.create();
                exitdialog.show();





            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Sign_up.class));
            }
        });


    }

    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() >= 8;
    }


}

