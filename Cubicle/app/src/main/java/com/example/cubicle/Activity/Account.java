package com.example.cubicle.Activity;

import java.util.ArrayList;

public class Account {

    public String id;
    public String name;
    public String email;
    public boolean verified;
    public ArrayList<Question> questionList;
    public ArrayList<Answer> answerList;

    public Account(String id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
        verified = false;
        questionList = new ArrayList<>();
        answerList = new ArrayList<>();
    }

    public void addQuestion(Question question){
        questionList.add(question);
    }

    public void answerQuestion(Answer answer){
        answerList.add(answer);
    }
}
