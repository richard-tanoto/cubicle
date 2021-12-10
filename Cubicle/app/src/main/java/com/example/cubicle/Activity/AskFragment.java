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
import android.widget.LinearLayout;

import com.example.cubicle.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class AskFragment extends Fragment {

    private RecyclerView recyclerView;
    private QuestionAdapter questionAdapter;
    private ImageButton add;
    private LinearLayout notFound;
    private ArrayList<Question> arrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ask, container, false);

        recyclerView = v.findViewById(R.id.questionRecyclerView);
        add = v.findViewById(R.id.add);
        notFound = v.findViewById(R.id.not_found);

        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Query query = FirebaseDatabase.getInstance().getReference().child("question").orderByChild("answered").equalTo(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()){
                    notFound.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        FirebaseRecyclerOptions<Question> questionOptions = new FirebaseRecyclerOptions.Builder<Question>()
                .setQuery(query, Question.class).build();
        questionAdapter = new QuestionAdapter(questionOptions);
        recyclerView.setAdapter(questionAdapter);

        add.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), PostQuestion.class);
            startActivity(intent);
        });

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        questionAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        questionAdapter.stopListening();
    }
}