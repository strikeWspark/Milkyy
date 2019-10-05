package com.dryfire.milkyy.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.dryfire.milkyy.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout usernameInput;
    private TextInputEditText usernameEdit;
    private TextInputLayout passwordInput;
    private TextInputEditText passwordEdit;
    private MaterialButton cancelButton;
    private MaterialButton nextButton;
    private MaterialButton signUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
                if (!isPasswordValid(passwordEdit.getText())) {
                    passwordInput.setError(getString(R.string.mky_error_password));
                } else {
                    passwordInput.setError(null);
                    startActivity(new Intent(MainActivity.this, HomeLayout.class));
                    finish();
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
                finish();
                System.exit(0);
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

