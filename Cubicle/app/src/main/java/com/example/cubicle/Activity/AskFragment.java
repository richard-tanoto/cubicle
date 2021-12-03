package com.example.cubicle.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.cubicle.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AskFragment extends Fragment {

    private RecyclerView recyclerView;
    private DatabaseReference database;
    private RecyclerView.Adapter questionAdapter;
    private ArrayList<Question> questionList;
    private ImageButton add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ask, container, false);

        recyclerView = v.findViewById(R.id.questions);
        add = v.findViewById(R.id.add);
        database = FirebaseDatabase.getInstance().getReference("question");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        questionList = new ArrayList<>();
        questionAdapter = new QuestionAdapter(getContext(), questionList);
        recyclerView.setAdapter(questionAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Question q = dataSnapshot.getValue(Question.class);
                    questionList.add(q);
                }
                questionAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });

        add.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), PostQuestion.class);
            startActivity(intent);
        });

        return v;

    }
}