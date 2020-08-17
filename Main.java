package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        QuizBuilder q = new QuizBuilder();
        q.go();

        QuizPlayer p = new QuizPlayer();
        p.go();
    }
}
