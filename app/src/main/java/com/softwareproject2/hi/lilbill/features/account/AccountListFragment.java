package com.softwareproject2.hi.lilbill.features.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softwareproject2.hi.lilbill.AccountLab;
import com.softwareproject2.hi.lilbill.R;
import com.softwareproject2.hi.lilbill.features.transaction.NewFriendActivity;
import com.softwareproject2.hi.lilbill.features.transaction.TransactionConstructionActivity;
import com.softwareproject2.hi.lilbill.features.transactionlist.TransactionListActivity;

import java.util.List;

/**
 * RecyclerViewer for all accounts
 */
public class AccountListFragment extends Fragment {

    private RecyclerView mTransactionRecyclerViewer;
    private AccountListFragment.AccountAdapter mAdapter;

    AccountLab accountLab = AccountLab.get(getActivity());


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_list, container, false);

        mTransactionRecyclerViewer = (RecyclerView) view.findViewById(R.id.account_recycler_view);
        //Layout manager to position the items in the recycler viewer
        mTransactionRecyclerViewer.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_account_list, menu);
    }

    /**
     * Menu bar
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_new_transaction:
                startActivity(new Intent(getActivity(), TransactionConstructionActivity.class));
                return true;
            case R.id.menu_add_friend:
                startActivity(new Intent(getActivity(), NewFriendActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Get the current data to populate
     */
    private void updateUI() {

        List<Account> accounts = accountLab.getAccounts();

        mAdapter = new AccountAdapter(accounts);
        mTransactionRecyclerViewer.setAdapter(mAdapter);
    }

    /**
     * Recycler list holder
     */
    private class AccountHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        private TextView mTileTextView;
        private TextView mUserTextView;

        private Account mAccount;

        public AccountHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_account, parent, false));
            itemView.setOnClickListener(this);

            mTileTextView = (TextView) itemView.findViewById(R.id.account_netbalance);
            mUserTextView = (TextView) itemView.findViewById(R.id.account_user2);
        }

        @Override
        public void onClick(View view) {

            //Set the intent for the transactionlist activity
            Intent intent = TransactionListActivity.newIntent(getActivity(), mAccount.getId());
            startActivity(intent);
        }

        public void bind(Account account) {

            Float balance = account.getNetBalance();
            String mAccountNetBalance = String.format("%.02f", balance)+ " kr.";

            mAccount = account;
            mTileTextView.setText(mAccountNetBalance);

            //Check which user is the one using the app
            String friend = account.getUser1();
            if (accountLab.getUser().getUsername().equals(friend)){
                friend = account.getUser2();
            }
            mUserTextView.setText(friend);
        }
    }

    /**
     * Recyclerlist adapter
     */
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
