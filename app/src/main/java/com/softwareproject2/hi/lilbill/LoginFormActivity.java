package com.softwareproject2.hi.lilbill;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.softwareproject2.hi.lilbill.features.account.AccountListActivity;


public class LoginFormActivity extends AppCompatActivity {

    /**
     * Activity which runs when you start the app. It contains a Login screen where you can enter a username
     * which exists in the database to login. If the username doesn't match an existing user, you get a Toast.
     */

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

                // result is a String which contains Account data if a correct username
                // has been enterd in the login form. Otherwise it is null
                String result = lab.logIn(mUsername, mPassword, context);

                // if results is null, perform toast.
                if(result.equals("null")) {
                    Toast toast = Toast.makeText(context, "User or password incorrect", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    startActivity(new Intent(LoginFormActivity.this, AccountListActivity.class));
                }
            }
        });
    }
}
