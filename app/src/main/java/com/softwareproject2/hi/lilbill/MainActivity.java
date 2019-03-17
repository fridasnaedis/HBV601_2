package com.softwareproject2.hi.lilbill;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Create and commit a fragment transaction
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new TransactionFragment();
            //creates and returns an instance of FragmentTransaction
            //Then we can chain fragments together
            fm.beginTransaction()
                    //(container view id, fragment)
                    //container view id tells where the fragments view should appear - unique identifyer
                    //this is the resource ID of the container view ID
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
