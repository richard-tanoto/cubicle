package com.example.cubicle.Activity;

import java.util.Calendar;
import java.util.Date;

public class Question {

    public enum Grade{
        ELEMENTARY_SCHOOL,
        MIDDLE_SCHOOL,
        HIGH_SCHOOL,
        ANY
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
        INFORMATION_TECHNOLOGY,
        OTHERS,
    }

    public String id;
    public String accountId;
    public Grade grade;
    public Subject subject;
    public String desc;
    public Date date;
    public boolean answered;

    public Question(){

    }

    public Question(String id, String accountId,String desc, Grade grade, Subject subject){
        this.id = id;
        this.accountId = accountId;
        this.desc = desc;
        this.grade = grade;
        this.subject = subject;
        date = Calendar.getInstance().getTime();
        answered = false;
    }
}
