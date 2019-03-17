package com.softwareproject2.hi.lilbill;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TransactionListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private TransactionAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction_list, container, false);

        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.transaction_recycler_view);
        //Gera layout manager til þess að sjá um að positiona items, líka til grid
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;

    }

    private void updateUI() {
        TransactionLab transactionLab = TransactionLab.get(getActivity());

        List<Transaction> transactions = transactionLab.getTransactions();

        mAdapter = new TransactionAdapter(transactions);
        mCrimeRecyclerView.setAdapter(mAdapter);
    }

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
            //TO DO
            //Tengja við fragment

            Toast.makeText(getActivity(), "hæ" , Toast.LENGTH_SHORT)
                .show();
        }

        public void bind(Transaction transaction) {
            mTransaction = transaction;
            mTileTextView.setText(mTransaction.getAmount().toString());
            mDateTextView.setText(mTransaction.getDate().toString());
        }
    }

    private class TransactionAdapter extends RecyclerView.Adapter<TransactionHolder> {
        private List<Transaction> mTransactions;

        public TransactionAdapter(List<Transaction> transactions) {
            mTransactions = transactions;
        }

        @Override
        public TransactionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new TransactionHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(TransactionHolder holder, int position) {
            Transaction crime = mTransactions.get(position);
            holder.bind(crime);

        }

        @Override
        public int getItemCount() {
            return mTransactions.size();
        }
    }

}
