package com.softwareproject2.hi.lilbill.features.transaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.softwareproject2.hi.lilbill.features.account.Account;
import com.softwareproject2.hi.lilbill.AccountLab;
import com.softwareproject2.hi.lilbill.R;
import com.softwareproject2.hi.lilbill.features.account.AccountListActivity;

import java.util.ArrayList;
import java.util.List;

public class TransactionConstructionFragment extends Fragment {
    private Transaction mTransaction;
    private EditText mAmountField;
    private EditText mDescriptionField;
    private TextView mSelectedAccountsList;
    private Button mSplitBetweenButton;
    private Button mSubmitButton;
    private String[] mListOfAccounts;
    private String[] accountIdList;
    boolean[] mCheckedAccounts;
    private ArrayList<Integer> mChosenAccounts = new ArrayList<>();

    private AccountLab accountLab = AccountLab.get(getActivity());



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_transaction_construction, container, false);
        mAmountField = (EditText) v.findViewById(R.id.transaction_amount);
        mDescriptionField = (EditText) v.findViewById(R.id.transaction_description);


        // Load friend names into selection object
        List<Account> accounts = accountLab.getAccounts();
        String currUsername = accountLab.getUser().getUsername();
        Integer maxSplit = 1;
        try {
            maxSplit = accounts.size()+1;
        } catch (Exception e){
        }


        mListOfAccounts = new String[maxSplit];
        accountIdList = new String[maxSplit];
        mListOfAccounts[0] = "Me";
        // Create a list of my accounts
        for (int i=1; i<maxSplit; i++){
            if (currUsername.equals(accounts.get(i-1).getUser1())) {
                mListOfAccounts[i] = accounts.get(i-1).getUser2();
            }
            else {
                mListOfAccounts[i] = accounts.get(i-1).getUser1();
            }
            accountIdList[i] = accounts.get(i-1).getId();
        }


        mCheckedAccounts = new boolean[mListOfAccounts.length];
        mSelectedAccountsList = (TextView) v.findViewById(R.id.selected_accounts);
        mSplitBetweenButton =  (Button) v.findViewById(R.id.split_between);
        mSplitBetweenButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Creates a pop-up view where accounts can be chosen from a list
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                mBuilder.setTitle("Split between:");
                mBuilder.setMultiChoiceItems(mListOfAccounts, mCheckedAccounts, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if(isChecked) {
                            if (!mChosenAccounts.contains(position)) {
                                mChosenAccounts.add(position);
                            }
                        } else {
                            if (mChosenAccounts.contains(position)) {
                                mChosenAccounts.remove(mChosenAccounts.indexOf(position));
                            }
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    // Handling which accounts have been chosen and showing them
                    public void onClick(DialogInterface dialog, int position) {
                        String chosenAccountsString = "";
                        for (int i = 0; i < mChosenAccounts.size(); i++) {
                            chosenAccountsString += mListOfAccounts[mChosenAccounts.get(i)];
                            if(i != mChosenAccounts.size()-1){
                                chosenAccountsString+=", ";
                            }
                        }
                        mSelectedAccountsList.setText(chosenAccountsString);
                    }
                });
                mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        dialog.dismiss();
                    }
                });
                mBuilder.setNeutralButton("Clear all", new DialogInterface.OnClickListener() {
                    @Override
                    // Removing all accounts from lisst of selected
                    public void onClick(DialogInterface dialog, int position) {
                        for(int i = 0; i < mCheckedAccounts.length; i++){
                           mCheckedAccounts[i] = false;
                        }
                        mChosenAccounts.clear();
                        mSelectedAccountsList.setText("");
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }});


        mSubmitButton = (Button) v.findViewById(R.id.submit_new_transaction);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mTransaction = new Transaction();
                if (mDescriptionField.getText(
                ) == null || mDescriptionField.getText().toString().equals("")) {
                    mTransaction.setDescription("No description");
                }
                else {
                    mTransaction.setDescription(mDescriptionField.getText().toString());
                }
                Boolean worked = true;
                // Send Transaction to chosen accounts
                for (Integer checked: mChosenAccounts){
                    if (checked>0){
                        if(!(mAmountField.getText().toString().isEmpty())){
                            mTransaction.setAmount(Float.valueOf(mAmountField.getText().toString())/mChosenAccounts.size());
                            mTransaction.setAccountId(accountIdList[checked]);
                            accountLab.createTransaction(mTransaction, accountIdList[checked]);
                        }
                        else {
                            Toast toast = Toast.makeText(getActivity(), "You must specify an amount!", Toast.LENGTH_SHORT);
                            toast.show();
                            worked = false;
                            break;
                        }

                    }

                }
                if(worked){
                    Toast toast = Toast.makeText(getActivity(), "Transaction added", Toast.LENGTH_SHORT);
                    toast.show();
                    startActivity(new Intent(getActivity(), AccountListActivity.class));
                }
            }
        });

        return v;
    }
}
