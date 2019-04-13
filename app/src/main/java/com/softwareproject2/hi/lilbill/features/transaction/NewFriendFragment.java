package com.softwareproject2.hi.lilbill.features.transaction;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.softwareproject2.hi.lilbill.AccountLab;
import com.softwareproject2.hi.lilbill.R;
import com.softwareproject2.hi.lilbill.SingleFragmentActivity;
import com.softwareproject2.hi.lilbill.features.account.Account;
import com.softwareproject2.hi.lilbill.features.account.AccountListActivity;
import com.softwareproject2.hi.lilbill.features.account.AccountListFragment;

import org.json.JSONException;

import java.io.IOException;


public class NewFriendFragment extends Fragment {
    private EditText mFriendUsername;
    Button mSubmitButton;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_friend, container, false);
        mFriendUsername = (EditText) v.findViewById(R.id.friend_name);
        mSubmitButton = (Button) v.findViewById(R.id.submit_new_friend);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: Do the stuff here
                final String accountId;
                AccountLab lab = AccountLab.get(getContext());
                String userId = lab.getUser().getId();
                String friendUsername = mFriendUsername.getText().toString();
                String response = new String();
                Toast toast;
                String error1 = "no user with username:";
                String error2 = "user with username:";

                try{
                    response = lab.addFriend(userId, friendUsername);
                    toast = Toast.makeText(getActivity(), "User added!", Toast.LENGTH_SHORT);
                    toast.show();
                }catch (Exception e){
                    if(response.contains(error1)){
                        toast = Toast.makeText(getActivity(), "This user does not exist", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else if (response.contains(error2)){
                        toast = Toast.makeText(getActivity(), "This user is already your friend", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    e.printStackTrace();
                }
                startActivity(new Intent(getActivity(), AccountListActivity.class));
            }
        });

        return v;
    }
}
