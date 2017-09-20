package services;

/**
 * Created by Camilla Velvin on 14.09.2017.
 */
public class Quiz {
    private String quizName;
    private Question[] questions;
    private String time;

    public Quiz() {}
    public Quiz(String quizName, Question[] questions, String time) {
        this.quizName = quizName;
        this.questions = questions;
        this.time = time;
    }
    public Question[] getQuestions() {
        return questions;
    }
    public String getTime() {
        return time;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
