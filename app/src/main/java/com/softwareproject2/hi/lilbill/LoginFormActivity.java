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

    /**
     * Activity sem keyrist fyrst við opnun á appi. Inniheldur form þar sem hægt er að logga sig
     * inn með username, ef að User er til í gagnagrunni, framkvæmir toast ef User er ekki til.
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

                // result er response frá login falli sem skilar User ef rétt username
                // hefur verið stimplað inn.
                String result = lab.logIn(mUsername, mPassword, context);

                // Ef results er null. Framkæma Toast og gera ekkert annað
                // Annars sýna AccountList Activity.
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
