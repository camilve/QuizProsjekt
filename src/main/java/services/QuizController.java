package services;

import java.util.ArrayList;

/**
 * Created by Camilla Velvin on 14.09.2017.
 */
public class QuizController {
    private ArrayList<ActiveQuiz> activeQuizs;

    public void addActiveQuiz(ActiveQuiz activeQuiz) {
        activeQuizs.add(activeQuiz);
    }
}
