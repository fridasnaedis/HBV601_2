package com.softwareproject2.hi.lilbill;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.softwareproject2.hi.lilbill.features.account.AccountListActivity;

import java.io.IOException;

public class LoginFormActivity extends AppCompatActivity {

    public EditText mUsernameField;
    private EditText mPasswordField;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUsernameField = (EditText) findViewById(R.id.username_input);
                mPasswordField = (EditText) findViewById(R.id.password_input);

                String mUsername = mUsernameField.getText().toString();
                String mPassword = mPasswordField.getText().toString();

                AccountLab lab = AccountLab.get(LoginFormActivity.this);
                Context context = getApplicationContext();
                Log.wtf("tag","test: before try");
                try {
                    Log.wtf("tag", "test: in try");
                    lab.logIn(mUsername, mPassword, context);
                    Log.wtf("tag", "test: after log");
                    startActivity(new Intent(LoginFormActivity.this, AccountListActivity.class));
                }
                catch (Exception e){
                    e.printStackTrace();
                    Log.wtf("tag","test: in catch");
                }
                Log.wtf("tag","test: after try");
            }

        });
    }
}
