package com.softwareproject2.hi.lilbill.features.transaction;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.softwareproject2.hi.lilbill.R;
import com.softwareproject2.hi.lilbill.SingleFragmentActivity;

public class TransactionActivity extends SingleFragmentActivity {

    public static final String TAG = TransactionActivity.class.getSimpleName();

    public static final String EXTRA_TRANSACTION_ID =
            "com.softwareproject2.hi.lilbill.transaction_id";

    public static Intent newIntent(Context packageContext, String transactionID) {
        Intent intent = new Intent(packageContext, TransactionActivity.class);
        intent.putExtra(EXTRA_TRANSACTION_ID, transactionID);
        return intent;
    }

    protected Fragment createFragment() {
        return new TransactionFragment();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        //Cool floating button for new transaction
        FloatingActionButton createNewTransaction = findViewById(R.id.new_transaction_fab);
        createNewTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TransactionActivity.this, TransactionConstructionActivity.class));
            }
        });
    }
}