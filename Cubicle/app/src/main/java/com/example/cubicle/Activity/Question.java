package com.example.cubicle.Activity;

import java.util.Calendar;
import java.util.Date;

public class Question {

    public enum Grade{
        ELEMENTARY_SCHOOL,
        MIDDLE_SCHOOL,
        HIGH_SCHOOL,
        NONE
    }

    public enum Subject{
        MATHEMATICS,
        PHYSICS,
        CHEMISTRY,
        BIOLOGY,
        ART,
        ECONOMY,
        HISTORY,
        GEOGRAPHY,
        CIVIC_EDUCATION,
        ENGLISH,
        INDONESIAN,
        OTHER_LANGUAGE,
        INFORMATION_TECHNOLOGY,
        OTHERS,
        NONE
    }

    private String id;
    public String accountId;
    public Grade grade;
    public Subject subject;
    public String question;
    public Date date;
    public boolean answered;

    public Question(String id, String accountId, String question, Grade grade, Subject subject){
        this.id = id;
        this.accountId = accountId;
        this.question = question;
        this.grade = grade;
        this.subject = subject;
        date = Calendar.getInstance().getTime();
        answered = false;
    }
}
