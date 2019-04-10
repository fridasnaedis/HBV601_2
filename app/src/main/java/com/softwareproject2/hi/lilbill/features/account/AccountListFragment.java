package com.softwareproject2.hi.lilbill.features.account;

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
import com.softwareproject2.hi.lilbill.R;
import com.softwareproject2.hi.lilbill.features.transactionlist.TransactionListActivity;

import java.util.List;

public class AccountListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private AccountListFragment.AccountAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_list, container, false);

        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.account_recycler_view);
        //Gera layout manager til þess að sjá um að positiona items, líka til grid
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;

    }

    private void updateUI() {

        AccountLab accountLab = AccountLab.get(getActivity());

        List<Account> accounts = accountLab.getAccounts();

        mAdapter = new AccountAdapter(accounts);
        mCrimeRecyclerView.setAdapter(mAdapter);
    }


    private class AccountHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        private TextView mTileTextView;
        private TextView mUserTextView;
        private TextView mDateTextView;

        private Account mAccount;

        public AccountHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_account, parent, false));
            itemView.setOnClickListener(this);

            //Ná í view layout í list_item_account
            //Búa til TextView til þess að populate-a recycle viewer

            mTileTextView = (TextView) itemView.findViewById(R.id.account_netbalance);
            mUserTextView = (TextView) itemView.findViewById(R.id.account_user2);

        }

        @Override
        public void onClick(View view) {
            //TO DO
            //Tengja við fragment

            //Intent intent = TransactionActivity.newAccountIntent(getActivity(), mAccount.getId());
            Intent intent = TransactionListActivity.newIntent(getActivity(), mAccount.getId());
            startActivity(intent);

        }

        public void bind(Account account) {

            //náum í netbalance og setjum í textViewið okkar og látum strenginn aðeins sýna 2 aukastafi
            Float balance = account.getNetBalance();
            String mAccountNetBalance = String.format("%.02f", balance)+ " kr.";

            mAccount = account;
            mTileTextView.setText(mAccountNetBalance);

            //gerum það sama fyrir user2
            String mAccountUser2 = account.getUser2();
            mUserTextView.setText(mAccountUser2);

        }
    }

    private class AccountAdapter extends RecyclerView.Adapter<AccountHolder> {
        private List<Account> mAccounts;

        public AccountAdapter(List<Account> accounts) {
            mAccounts = accounts;
        }

        @Override
        public AccountHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new AccountHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(AccountListFragment.AccountHolder holder, int position) {
            Account account = mAccounts.get(position);
            holder.bind(account);
        }

        @Override
        public int getItemCount() {
            return mAccounts.size();
        }
    }
}
