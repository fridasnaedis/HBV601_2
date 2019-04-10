package com.softwareproject2.hi.lilbill.features.transactionlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softwareproject2.hi.lilbill.AccountLab;
import com.softwareproject2.hi.lilbill.MainActivity;
import com.softwareproject2.hi.lilbill.R;
import com.softwareproject2.hi.lilbill.TransactionLab;
import com.softwareproject2.hi.lilbill.features.account.Account;
import com.softwareproject2.hi.lilbill.features.transaction.Transaction;

import java.util.List;
import java.util.UUID;

public class TransactionListFragment extends Fragment {


    private RecyclerView mTransactionRecyclerView;
    private TransactionAdapter mAdapter;
    private Account mAccount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction_list, container, false);

        UUID accountId = (UUID) getActivity().getIntent()
                .getSerializableExtra(TransactionListActivity.EXTRA_ACCOUNT_ID);

        mAccount = AccountLab.get(getActivity()).getAccount(accountId);


        mTransactionRecyclerView = (RecyclerView) view.findViewById(R.id.transaction_recycler_view);
        //Gera layout manager til þess að sjá um að positiona items, líka til grid
        mTransactionRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        //ýtra í öfugri röð

        List<Transaction> transactions = mAccount.getTransactionsList();

        mAdapter = new TransactionAdapter(transactions);
        mTransactionRecyclerView.setAdapter(mAdapter);
    }

    //Holder sér um að inflate-a layout
    private class TransactionHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        private TextView mTileTextView;
        private TextView mDateTextView;

        private Transaction mTransaction;

        public TransactionHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_transaction, parent, false));
           itemView.setOnClickListener(this);

            mTileTextView = (TextView) itemView.findViewById(R.id.transaction_amount);
            mDateTextView = (TextView) itemView.findViewById(R.id.transaction_date);
        }

        @Override
        public void onClick(View view) {


            Intent intent = MainActivity.newIntent(getActivity(), mTransaction.getId(), mAccount.getId());
            startActivity(intent);

        }

        //bindur Java kóða og tengir við widget
        public void bind(Transaction transaction) {
            String mTransactionAmount = transaction.getAmount().toString() + " kr.";
            mTransaction = transaction;
            mTileTextView.setText(mTransactionAmount);
            mDateTextView.setText(mTransaction.getDate().toString());
        }
    }

    //Adapter sér um að gefa RecyclerView upplýsingar um transactions
    private class TransactionAdapter extends RecyclerView.Adapter<TransactionHolder> {

        private List<Transaction> mTransactions;

        public TransactionAdapter(List<Transaction> transactions) {
            mTransactions = transactions;
        }

        //recyclerviewer kallar á onCreateViewHolder til þess að birta hlut
        @Override
        public TransactionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new TransactionHolder(layoutInflater, parent);
        }

        //When the recyclerView request that a given transactionholder to be bound to a particular transaction
        @Override
        public void onBindViewHolder(TransactionHolder holder, int position) {
            Transaction transaction = mTransactions.get(position);
            holder.bind(transaction);

        }

        @Override
        public int getItemCount() {
            return mTransactions.size();
        }
    }

}
