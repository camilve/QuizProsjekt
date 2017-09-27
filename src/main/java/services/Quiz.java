package services;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Camilla Velvin on 14.09.2017.
 */
public class Quiz {
    private String quizName;
    private Question[] questions;
    private String tid;
    private Date endTime;

    public Quiz() {

    }
    public Quiz(String quizName, Question[] questions, String tid) {
        this.quizName = quizName;
        this.questions = questions;
        this.tid = tid;
        String quizdate = tid;
        quizdate = quizdate.replace("T", " ");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        try {
            date = format.parse(quizdate);
        } catch (Exception e) {

        }
        for(int i=0; i<questions.length; i++) {
            date.setSeconds(date.getSeconds() + questions[i].getSeconds());
        }
        endTime = date;
    }
    public Question[] getQuestions() {
        return questions;
    }
    public String getTid() {
        return tid;
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

    public void setTid(String tid) {
        this.tid = tid;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public void addEndTime() {
        String quizdate = tid;
        quizdate = quizdate.replace("T", " ");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        try {
            date = format.parse(quizdate);
        } catch (Exception e) {

        }
        for(int i=0; i<questions.length; i++) {
            date.setSeconds(date.getSeconds() + questions[i].getSeconds());
        }
        endTime = date;
    }

}
