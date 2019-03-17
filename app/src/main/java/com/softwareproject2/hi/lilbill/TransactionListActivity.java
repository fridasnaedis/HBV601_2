package com.softwareproject2.hi.lilbill;

import android.support.v4.app.Fragment;

public class TransactionListActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return new TransactionListFragment();
    }
}
