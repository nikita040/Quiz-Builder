package com.company;

import javax.swing.*;

public class QuizCard {
    String ques;
    String ans;

    public QuizCard(String q, String a){
        ques = q;
        ans = a;
    }

    String getQuestion(){
        return ques;
    }

    public String getAnswer(){
        return ans;
    }
}
