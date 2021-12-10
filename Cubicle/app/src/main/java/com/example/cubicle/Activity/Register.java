package com.example.cubicle.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cubicle.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText password;
    private EditText verifyPassword;
    private CheckBox checkBox;
    private ProgressBar progressBar;
    private Button button;
    private TextView signIn;

    private FirebaseAuth fAuth;
    private DatabaseReference databaseAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.registerName);
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);
        verifyPassword = findViewById(R.id.registerVerifyPassword);
        progressBar = findViewById(R.id.registerProgressBar);
        button = findViewById(R.id.registerButton);
        signIn = findViewById(R.id.registerSignIn);
        checkBox = findViewById(R.id.registerCheckBox);

        progressBar.setVisibility(View.INVISIBLE);

        fAuth = FirebaseAuth.getInstance();
        databaseAccount = FirebaseDatabase.getInstance().getReference("account");

        signIn.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        });

        button.setOnClickListener(view -> {
            button.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            final String iName = name.getText().toString();
            final String iEmail = email.getText().toString();
            final String iPassword = password.getText().toString();
            final String iVerifyPassword = verifyPassword.getText().toString();

            if (!checkBox.isChecked()){
                Toast.makeText(getApplicationContext(), "Please click the check box to continue.", Toast.LENGTH_LONG).show();
                button.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
            }
            else if (!iPassword.equals(iVerifyPassword)){
                Toast.makeText(getApplicationContext(), "Password not match.", Toast.LENGTH_LONG).show();
                button.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
            }
            else if (iName.isEmpty() || iEmail.isEmpty() || iPassword.isEmpty() || iVerifyPassword.isEmpty()){
                Toast.makeText(getApplicationContext(), "Please fill in the blanks.", Toast.LENGTH_LONG).show();
                button.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
            }
            else{
                fAuth.createUserWithEmailAndPassword(iEmail, iPassword).addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        String id = fAuth.getCurrentUser().getUid();
                        Account account = new Account(id, iName, iEmail);
                        databaseAccount.child(id).setValue(account);
                        Toast.makeText(getApplicationContext(), "Account created successfully!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        button.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });
    }
}