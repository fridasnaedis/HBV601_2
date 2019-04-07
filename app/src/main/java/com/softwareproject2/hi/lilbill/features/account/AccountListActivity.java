package com.softwareproject2.hi.lilbill.features.account;

import android.support.v4.app.Fragment;

import com.softwareproject2.hi.lilbill.SingleFragmentActivity;

public class AccountListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new AccountListFragment();
    }
}

