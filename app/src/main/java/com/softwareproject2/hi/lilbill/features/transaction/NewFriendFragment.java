package com.softwareproject2.hi.lilbill.features.transaction;

import android.content.Context;
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
                AccountLab lab = AccountLab.get(getContext());
                String userId = lab.getUser().getId();
                String friendUsername = mFriendUsername.getText().toString();

                String response = lab.addFriend(userId, friendUsername);

                JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();

                final String error = jsonObject.get("error").getAsString();
                if(error.isEmpty())
                {
                    Toast toast = Toast.makeText(getActivity(), "User added!", Toast.LENGTH_SHORT);
                    toast.show();
                    getActivity().finish();
                }
                else {
                    Toast toast = Toast.makeText(getActivity(), "This user does not exist", Toast.LENGTH_SHORT);
                    toast.show();
                }

                //Toast toast = Toast.makeText(getActivity(), "This has not been implemented yet", Toast.LENGTH_SHORT);
                //toast.show();
            }
        });

        return v;
    }
}
