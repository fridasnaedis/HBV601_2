package com.softwareproject2.hi.lilbill;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.softwareproject2.hi.lilbill.features.account.AccountListActivity;

public class LoginFormActivity extends AppCompatActivity {

    public EditText mUsernameField;
    private EditText mPasswordField;
    AccountLab lab = AccountLab.get(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        mUsernameField = (EditText) findViewById(R.id.username_input);
        mPasswordField = (EditText) findViewById(R.id.password_input);

        String mUsername = mUsernameField.getText().toString();
        String mPassword = mPasswordField.getText().toString();

        lab.login(mUsername, mPassword);

        Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginFormActivity.this, AccountListActivity.class));
            }
        });
    }
}
