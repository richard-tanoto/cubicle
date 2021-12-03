package com.example.cubicle.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.cubicle.R;

public class PostQuestion extends AppCompatActivity {

    private Button elementary;
    private Button middle;
    private Button high;
    private EditText subject;
    private EditText question;
    private ImageButton attach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_question);

        elementary = findViewById(R.id.elementary);
        middle = findViewById(R.id.middle);
        high = findViewById(R.id.high);
        subject = findViewById(R.id.subject);
        question = findViewById(R.id.question);
        attach = findViewById(R.id.attachFile);
    }
}