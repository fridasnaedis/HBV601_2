package com.softwareproject2.hi.lilbill.features.transaction;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.softwareproject2.hi.lilbill.AccountLab;
import com.softwareproject2.hi.lilbill.R;
import com.softwareproject2.hi.lilbill.features.account.AccountListActivity;

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

                //Get the current lab and user
                AccountLab lab = AccountLab.get(getContext());
                String userId = lab.getUser().getId();

                //Get the name of the friend to add
                String friendUsername = mFriendUsername.getText().toString();

                //Initialize strings
                String response;
                Toast toast;
                String error1 = "no user with username:";
                String error2 = "user with username:";

                //Get the json response from the lab and handle corresponting errors
                response = lab.addFriend(userId, friendUsername);
                if(response.contains(error1)){
                    toast = Toast.makeText(getActivity(), "This user does not exist", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if (response.contains(error2)){
                    toast = Toast.makeText(getActivity(), "This user is already your friend", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    toast = Toast.makeText(getActivity(), "User added!", Toast.LENGTH_SHORT);
                    toast.show();
                }

                //Go back to the AccountList Activity
                startActivity(new Intent(getActivity(), AccountListActivity.class));
            }
        });

        return v;
    }
}
