package services;

import java.util.*;

/**
 * Created by Camilla Velvin on 14.09.2017.
 */
public class ActiveQuiz {
    private Quiz quiz;
    private String id;
    private ArrayList<Participant> quizzers = new ArrayList<Participant>(); //nickname with points
    private int spmnr = 0;
    private int questionCount;

    public ActiveQuiz() {
    }
    public ActiveQuiz(Quiz quiz) {
        this.quiz = quiz;
        id = UUID.randomUUID().toString().replace("-", "");
        questionCount = quiz.getQuestions().length;
    }

    public Quiz getQuiz() {
        return quiz;
    }
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
    public String getId (){
        return id;
    }
    public void addParticipant(String nickname) {
        quizzers.add(new Participant(nickname));
    }

    public void givePoints(String nickname, int answer) {
        int[] correctAnswers = quiz.getQuestions()[spmnr].getCorrect();
        for(int j=0; j<correctAnswers.length; j++) {
            if(correctAnswers[j] == answer) {
                for(int i=0; i<quizzers.size(); i++) {
                    if(quizzers.get(i).getNickname().equals(nickname)) {
                        int point = quizzers.get(i).getPoints();
                        quizzers.get(i).setPoints(point+1);
                    }
                }
            }
        }
    }
    public void nextQuestion() {
        if(spmnr < questionCount) {
            spmnr++;
        }
    }

    public List<Participant> scoreboard() {
        ArrayList<Participant> scoreBoard = quizzers;

        Collections.sort(scoreBoard);
        int length = 4;
        if(length > scoreBoard.size()) {
            length = scoreBoard.size();
        }

        return scoreBoard.subList(0,length);
    }

}
