package com.example.cubicle.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cubicle.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button button;
    private ProgressBar progressBar;
    private TextView signUp;
    private TextView clickHere;
    private FirebaseAuth fAuth;
    private DatabaseReference databaseAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);
        button = findViewById(R.id.loginButton);
        progressBar = findViewById(R.id.loginProgressBar);
        signUp = findViewById(R.id.loginSignUp);
        clickHere = findViewById(R.id.loginClickHere);

        progressBar.setVisibility(View.INVISIBLE);

        fAuth = FirebaseAuth.getInstance();
        databaseAccount = FirebaseDatabase.getInstance().getReference("account");

        signUp.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Register.class);
            startActivity(intent);
        });

        clickHere.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ForgotPassword.class);
            startActivity(intent);
        });

        button.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            button.setVisibility(View.INVISIBLE);

            final String iEmail = email.getText().toString();
            final String iPassword = password.getText().toString();

            if (iEmail.isEmpty() || iPassword.isEmpty()){
                progressBar.setVisibility(View.INVISIBLE);
                button.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "The email or password cannot be empty.", Toast.LENGTH_LONG).show();
            }
            else{
                fAuth.signInWithEmailAndPassword(iEmail, iPassword).addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        databaseAccount.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                        startActivity(intent);
                    }
                    else{
                        progressBar.setVisibility(View.INVISIBLE);
                        button.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = fAuth.getCurrentUser();

        if (user != null){
            Intent intent = new Intent(getApplicationContext(), MainMenu.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {

    }
}