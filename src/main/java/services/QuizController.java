package services;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Camilla Velvin on 14.09.2017.
 */
public class QuizController {
    private ArrayList<ActiveQuiz> activeQuizs = new ArrayList<ActiveQuiz>();

    public void addActiveQuiz(ActiveQuiz activeQuiz) {
        activeQuizs.add(activeQuiz);
    }
    public List<ActiveQuiz> getActiveQuizs() {
        return activeQuizs;
    }

    public List<ActiveQuiz> getFutureQuizs() {
        ArrayList<ActiveQuiz> futureQuizs = new ArrayList<ActiveQuiz>();
        for(ActiveQuiz quiz : activeQuizs) {
            Date today = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = new Date();
            try {
                date = format.parse(quiz.getQuiz().getTime());
            } catch (Exception e) {

            }
            if(today.before(date)) {
                futureQuizs.add(quiz);
            }
        }
        return futureQuizs;
    }
}
