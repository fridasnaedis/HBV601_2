package com.softwareproject2.hi.lilbill.features.transaction;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.softwareproject2.hi.lilbill.R;

public class NewFriendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friend);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container_new_friend);

        if (fragment == null) {
            fragment = new NewFriendFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container_new_friend, fragment)
                    .commit();
        }
    }
}
