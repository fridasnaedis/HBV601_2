package com.softwareproject2.hi.lilbill.features.transaction;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.softwareproject2.hi.lilbill.R;

public class TransactionConstructionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_construction);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container_transaction_construction);

        if (fragment == null) {
            fragment = new TransactionConstructionFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container_transaction_construction, fragment)
                    .commit();
        }
    }
}
