package com.example.cubicle.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.cubicle.R;
import com.google.firebase.auth.FirebaseAuth;

public class RecoveryLinkMessage extends AppCompatActivity {

    private Button button;
    private ProgressBar progressBar;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery_link_message);

        fAuth = FirebaseAuth.getInstance();
        fAuth.sendPasswordResetEmail(getIntent().getStringExtra("keyEmail"));

        button = findViewById(R.id.OK);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);

        button.setOnClickListener(view -> {
            button.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);

            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        });
    }
}