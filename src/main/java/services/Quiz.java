package services;

import java.util.Date;

/**
 * Created by Camilla Velvin on 14.09.2017.
 */
public class Quiz {
    private Question[] questions;
    private Date tid;

    public Quiz(Question[] questions, Date tid) {
        this.questions = questions;
        this.tid = tid;
    }
    public Question[] getQuestion() {
        return questions;
    }
    public Date getTid() {
        return tid;
    }
}
