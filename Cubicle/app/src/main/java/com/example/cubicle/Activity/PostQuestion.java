package com.example.cubicle.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cubicle.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostQuestion extends AppCompatActivity {

    private RadioGroup gradeRadioGroup;
    private RadioButton elementaryButton, middleButton, highButton;
    private Spinner subjectSpinner;
    private EditText descEditText;
    private ImageButton attach;
    private TextView add;
    private Question.Grade grade;
    private DatabaseReference databaseQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_question);

        gradeRadioGroup = findViewById(R.id.gradeRadioGroup);
        elementaryButton = findViewById(R.id.elementary);
        middleButton = findViewById(R.id.middle);
        highButton = findViewById(R.id.high);
        subjectSpinner = findViewById(R.id.subjectSpinner);
        descEditText = findViewById(R.id.question);
        attach = findViewById(R.id.attachFile);
        add = findViewById(R.id.add);

        databaseQuestion = FirebaseDatabase.getInstance().getReference("question");

        gradeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id){
                    case R.id.elementary:
                        grade = Question.Grade.ELEMENTARY_SCHOOL;
                        break;
                    case R.id.middle:
                        grade = Question.Grade.MIDDLE_SCHOOL;
                        break;
                    case R.id.high:
                        grade = Question.Grade.HIGH_SCHOOL;
                        break;
                    default:
                        grade = Question.Grade.ANY;
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = databaseQuestion.push().getKey();
                Question.Subject subj = Question.Subject.valueOf(subjectSpinner.getSelectedItem().toString().toUpperCase());
                String desc = descEditText.getText().toString();
                if (desc.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please fill in the question.", Toast.LENGTH_LONG).show();
                }
                else{
                    String accountId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    Question question = new Question(id, accountId,  desc, grade, subj);
                    databaseQuestion.child(id).setValue(question);
                    Toast.makeText(getApplicationContext(), "Question created successfully.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}