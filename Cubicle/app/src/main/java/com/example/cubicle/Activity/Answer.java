package com.example.cubicle.Activity;

public class Answer {

    private String id;
    public String accountId;
    public String questionId;
    public String answer;

    public Answer(String id, String accountId, String questionId, String answer){
        this.id = id;
        this.accountId = accountId;
        this.questionId = questionId;
        this.answer = answer;
    }

}
