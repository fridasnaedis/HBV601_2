package com.softwareproject2.hi.lilbill.features.transaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import java.util.ArrayList;

public class TransactionConstructionFragment extends Fragment {
    private Transaction mTransaction;
    private EditText mAmountField;
    private EditText mDescriptionField;
    TextView mSelectedAccountsList;
    Button mSplitBetweenButton;
    Button mSubmitButton;
    String[] listItems; //  = {"Sara", "Fríða", "Ísak", "Júlli", "Palli"}
    String[] accountIdList;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();

    AccountLab accountLab = AccountLab.get(getActivity());



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTransaction = new Transaction();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_transaction_construction, container, false);
        mAmountField = (EditText) v.findViewById(R.id.transaction_amount);
        mDescriptionField = (EditText) v.findViewById(R.id.transaction_description);


        // Load friend names into selection object
        List<Account> accounts = accountLab.getAccounts();
        String currUsername = accountLab.getUser().getUsername();
        listItems = new String[accounts.size() + 1];
        accountIdList = new String[accounts.size() + 1];
        listItems[0] = "Me";
        for (int i=1; i<=accounts.size(); i++){
            if (currUsername.equals(accounts.get(i).getUser1())) {
                listItems[i] = accounts.get(i).getUser2();
            }
            else {
                listItems[i] = accounts.get(i).getUser2();
            }
            accountIdList[i] = accounts.get(i).getId();  // Held að sara hafi verið að fixa
        }


        checkedItems = new boolean[listItems.length];
        mSelectedAccountsList = (TextView) v.findViewById(R.id.selected_accounts);
        mSplitBetweenButton =  (Button) v.findViewById(R.id.split_between);
        mSplitBetweenButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                mBuilder.setTitle("Split between:");
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if(isChecked) {
                            if (!mUserItems.contains(position)) {
                                mUserItems.add(position);
                            }
                        } else {
                            if (mUserItems.contains(position)) {
                                mUserItems.remove(mUserItems.indexOf(position));
                            }
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        String itemNames = "";
                        for (int i = 0; i < mUserItems.size(); i++) {
                            itemNames += listItems[mUserItems.get(i)];
                            if(i != mUserItems.size()-1){
                                itemNames+=", ";
                            }
                        }
                        mSelectedAccountsList.setText(itemNames);
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
                    public void onClick(DialogInterface dialog, int position) {
                        for( int i = 0; i < checkedItems.length; i++){
                           checkedItems[i] = false;
                        }
                        mUserItems.clear();
                        mSelectedAccountsList.setText("");
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }});


        mSubmitButton = (Button) v.findViewById(R.id.submit_new_transaction);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // mTransaction.setAmount(Float.valueOf(mAmountField.getText().toString()));
                // mTransaction.setDescription(mDescriptionField.getText().toString());

                if (mDescriptionField.getText() == null || mDescriptionField.getText().toString().equals("")) {
                    mTransaction.setDescription("");
                }

                // TODO: Handle transaction things here

                for (Integer checked: mUserItems){
                    if (checked>0){
                        mTransaction.setAmount(Float.valueOf(mAmountField.getText().toString())/mUserItems.size());
                        mTransaction.getAccountId(accountIdList[checked]);
                        accountLab.createTransaction(mTransaction, accountIdList[checked]);
                    }
                }

                //TransactionLab.get(getActivity()).addTransaction(mTransaction);
                getActivity().finish();
            }
        });

        return v;
    }
}
