package com.example.cubicle.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cubicle.R;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Question> questionList;

    public QuestionAdapter(Context context, ArrayList<Question> questionList){
        this.context = context;
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Question q = questionList.get(position);
        holder.question.setText(q.question);
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView question;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            question = itemView.findViewById(R.id.question);
        }
    }
}
