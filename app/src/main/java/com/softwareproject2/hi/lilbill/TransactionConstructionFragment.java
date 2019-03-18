package com.softwareproject2.hi.lilbill;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TransactionConstructionFragment extends Fragment {
    private Transaction mTransaction;
    private EditText mAmountField;
    Button submit_button;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTransaction = new Transaction();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_transaction_construction, container, false);

        submit_button = (Button) v.findViewById(R.id.submit_new_transaction);
        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(getActivity(), "This has not been implemented yet", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        return v;
    }
}
