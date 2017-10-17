package entities;

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
    private String[] ages = new String[5];

    public Quiz() {

    }
    public Quiz(String quizName, Question[] questions, String tid, String[] ages) {
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
            e.printStackTrace();
        }
        for (Question question : questions) {
            date.setSeconds(date.getSeconds() + question.getSeconds());
        }
        endTime = date;
        this.ages = ages;
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

    // calculates the end time for the quiz, based on start time and time on each question.
    public void addEndTime() {
        String quizdate = tid;
        quizdate = quizdate.replace("T", " ");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        try {
            date = format.parse(quizdate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Question question : questions) {
            date.setSeconds(date.getSeconds() + question.getSeconds());
        }
        endTime = date;
    }

    public String[] getAges() {
        return ages;
    }

    public void setAges(String[] ages) {
        if(ages.length ==5 ) {
            this.ages = new String[1];
            this.ages[0] = "All ages!";
        } else {
            this.ages = ages;
        }
    }
}
