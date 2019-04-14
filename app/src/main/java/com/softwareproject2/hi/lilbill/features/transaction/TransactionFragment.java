package com.softwareproject2.hi.lilbill.features.transaction;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softwareproject2.hi.lilbill.AccountLab;
import com.softwareproject2.hi.lilbill.R;

/**
 * Transaction fragment
 */
public class TransactionFragment extends Fragment {

    public static final String TAG = TransactionActivity.class.getSimpleName();

    private Transaction mTransaction;
    private TextView mDate;
    private TextView mDecription;
    private TextView mAmount;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String transactionId = (String) getActivity().getIntent()
                .getSerializableExtra(TransactionActivity.EXTRA_TRANSACTION_ID);
        mTransaction = AccountLab.get(getActivity()).getTransaction(transactionId);
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
        mDate.setText(mTransaction.getDate());

        return v;
    }

}