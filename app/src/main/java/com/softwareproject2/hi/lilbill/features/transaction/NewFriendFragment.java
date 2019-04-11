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

import com.softwareproject2.hi.lilbill.R;


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
                Toast toast = Toast.makeText(getActivity(), "This has ginot been implemented yet", Toast.LENGTH_SHORT);
                toast.show();
                // TODO: Do the stuff here
                getActivity().finish();
            }
        });

        return v;
    }
}
