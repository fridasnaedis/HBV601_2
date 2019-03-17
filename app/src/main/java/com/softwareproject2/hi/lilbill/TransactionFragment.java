package com.softwareproject2.hi.lilbill;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TransactionFragment extends Fragment {

    private Transaction mTransaction;
    private TextView mDate;
    private TextView mDecription;
    private TextView mAmount;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTransaction = new Transaction();

        //Dummy data
        Float value = new Float(2000);
        mTransaction.setAmount(value);
        mTransaction.setDescription("Dominos");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //(layout recource ID, view's parent, add inflated code or not to parents view's
        View v = inflater.inflate(R.layout.fragment_transactionview, container, false);


        mAmount = (TextView) v.findViewById(R.id.transaction_amount);
        mAmount.setText(mTransaction.getAmount().toString());



        mDecription = (TextView) v.findViewById(R.id.transaction_description);
        mDecription.setText(mTransaction.getDescription());



        mDate = (TextView) v.findViewById(R.id.transaction_date);
        mDate.setText(mTransaction.getDate().toString());


        return v;

    }

}