package com.example.cubicle.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cubicle.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText email;
    private Button button;
    private ProgressBar progressBar;

    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = findViewById(R.id.resetEmail);
        button = findViewById(R.id.resetButton);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);

        fAuth = FirebaseAuth.getInstance();

        button.setOnClickListener(view -> {
            button.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);

            final String iEmail = email.getText().toString();
            if (iEmail.isEmpty()){
                Toast.makeText(getApplicationContext(), "Please insert your email.", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
                button.setVisibility(View.VISIBLE);
            }
            else{
                Intent intent = new Intent(getApplicationContext(), RecoveryLinkMessage.class);
                intent.putExtra("keyEmail", iEmail);
                startActivity(intent);
            }
        });
    }
}