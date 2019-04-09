package com.softwareproject2.hi.lilbill.features.transactionlist;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.softwareproject2.hi.lilbill.MainActivity;
import com.softwareproject2.hi.lilbill.SingleFragmentActivity;

import java.util.UUID;

public class TransactionListActivity extends SingleFragmentActivity {

    public static final String EXTRA_ACCOUNT_ID =
            "com.softwareproject2.hi.lilbill.account_id";


    public static Intent newIntent(Context packageContext, UUID accountID) {
        Intent intent = new Intent(packageContext, TransactionListActivity.class);
        intent.putExtra(EXTRA_ACCOUNT_ID, accountID);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new TransactionListFragment();
    }


}
