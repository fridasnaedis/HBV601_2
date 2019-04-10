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
import com.softwareproject2.hi.lilbill.MainActivity;
import com.softwareproject2.hi.lilbill.R;
import com.softwareproject2.hi.lilbill.TransactionLab;
import com.softwareproject2.hi.lilbill.features.transactionlist.TransactionListActivity;

import java.util.UUID;

public class TransactionFragment extends Fragment {

    public static final String TAG = MainActivity.class.getSimpleName();

    private Transaction mTransaction;
    private TextView mDate;
    private TextView mDecription;
    private TextView mAmount;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mTransaction = new Transaction();

        UUID accountId = (UUID) getActivity().getIntent()
                .getSerializableExtra(TransactionListActivity.EXTRA_ACCOUNT_ID);

        UUID transactionId = (UUID) getActivity().getIntent()
                .getSerializableExtra(MainActivity.EXTRA_TRANSACTION_ID);

        // TO FIX - tengja account id og kalla með báðum id's
       // mTransaction = AccountLab.get(getActivity()).getTransaction(transactionId, accountId);

        mTransaction = AccountLab.get(getActivity()).getTransaction(transactionId);


//        Transaction transaction = new Transaction();
//        transaction.setAccountId(Long.valueOf("1"));
//        transaction.setAmount(Float.valueOf("1321321"));
//        transaction.setDate(new Date());
//        transaction.setDescription("this is a transaction");
//        transaction.setSplitId(Long.valueOf("2"));
//
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();
//
//        Log.d(TAG, gson.toJson(transaction));

        //Dummy data
        //Float value = new Float(200000);
        //mTransaction.setAmount(value);
        //mTransaction.setDescription("Dominos Pizza er góð! Segir Ísak!");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //(layout recource ID, view's parent, add inflated code or not to parents view's
        View v = inflater.inflate(R.layout.fragment_transactionview, container, false);


        mAmount = (TextView) v.findViewById(R.id.transaction_amount);
        mAmount.setText(mTransaction.getAmount().toString());
        //mAmount.setText("1234");



        mDecription = (TextView) v.findViewById(R.id.transaction_description);
        mDecription.setText(mTransaction.getDescription());
        //mDecription.setText("test");


        // android.text.format.DateFormat
        // setja dagsettningu á læsilegra form
        mDate = (TextView) v.findViewById(R.id.transaction_date);
        mDate.setText(mTransaction.getDate().toString());


        return v;

    }

}