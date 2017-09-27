package controllers;

import entities.ActiveQuiz;

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

    public List<ActiveQuiz> getOngoingQuizzes() {
        ArrayList<ActiveQuiz> ongoingquizzes = new ArrayList<ActiveQuiz>();
        for(ActiveQuiz quiz: activeQuizs) {
            Date today = new Date();
            String quizdate = quiz.getQuiz().getTid();
            quizdate = quizdate.replace("T", " ");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = new Date();
            try {
                date = format.parse(quizdate);
            } catch (Exception e) {

            }
            if(today.before(quiz.getQuiz().getEndTime()) && date.before(today)) {
                ongoingquizzes.add(quiz);
            }
        }
        return ongoingquizzes;
    }

    public List<ActiveQuiz> getPreviousActiveQuizs() {
        ArrayList<ActiveQuiz> previousquizzes = new ArrayList<ActiveQuiz>();
        Date now = new Date();
        for(ActiveQuiz quiz : activeQuizs) {
            if(quiz.getQuiz().getEndTime().before(now)) {
                previousquizzes.add(quiz);
            }
        }
        return previousquizzes;
    }

    public List<ActiveQuiz> getFutureQuizs() {
        ArrayList<ActiveQuiz> futureQuizs = new ArrayList<ActiveQuiz>();
        for(ActiveQuiz quiz : activeQuizs) {
            Date today = new Date();
            String quizdate = quiz.getQuiz().getTid();
            quizdate = quizdate.replace("T", " ");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = new Date();
            try {
                date = format.parse(quizdate);
            } catch (Exception e) {

            }
            if(today.before(date)) {
                futureQuizs.add(quiz);
            }
        }
        return futureQuizs;
    }

    public ActiveQuiz findActiveQuiz(String id) {
        for(ActiveQuiz quiz : activeQuizs) {
            if(quiz.getId().equals(id)) {
                return quiz;
            }
        }
        return null;
    }
}
