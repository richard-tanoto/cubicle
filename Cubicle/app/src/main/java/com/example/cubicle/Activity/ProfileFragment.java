package com.example.cubicle.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cubicle.R;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {

    private Button button;

    private FirebaseAuth fAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        button = v.findViewById(R.id.logoutButton);

        fAuth = FirebaseAuth.getInstance();

        button.setOnClickListener(view -> {
            fAuth.signOut();
            Intent intent = new Intent(getActivity(), Login.class);
            startActivity(intent);
        });

        return v;
    }
}