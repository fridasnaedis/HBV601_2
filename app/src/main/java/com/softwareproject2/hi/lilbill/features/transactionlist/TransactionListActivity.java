package com.softwareproject2.hi.lilbill.features.transactionlist;

import android.support.v4.app.Fragment;

import com.softwareproject2.hi.lilbill.SingleFragmentActivity;

public class TransactionListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new TransactionListFragment();
    }
}
