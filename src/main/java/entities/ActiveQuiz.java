package entities;

import java.util.*;

/**
 * Created by Camilla Velvin on 14.09.2017.
 */

/**
 * An active quiz is a quiz with participants and id.
 */
public class ActiveQuiz {
    private Quiz quiz;
    private String id;
    private ArrayList<Participant> quizzers = new ArrayList<Participant>(); //nickname with points

    public ActiveQuiz() {
        id = UUID.randomUUID().toString().replace("-", "");
    }
    public ActiveQuiz(Quiz quiz) {
        this.quiz = quiz;
        id = UUID.randomUUID().toString().replace("-", "");
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

    public ArrayList<Participant> getQuizzers() {
        return quizzers;
    }

    public void setQuizzers(ArrayList<Participant> quizzers) {
        this.quizzers = quizzers;
    }

    public void addParticipant(String nickname) {
        quizzers.add(new Participant(nickname));
    }

    public void givePoints(String nickname, int answer, int questionNumber) {
        int[] correctAnswers = quiz.getQuestions()[questionNumber].getCorrect();
        for (int correctAnswer : correctAnswers) {
            if (correctAnswer == answer) {
                for (Participant quizzer : quizzers) {
                    if (quizzer.getNickname().equals(nickname)) {
                        int point = quizzer.getPoints();
                        quizzer.setPoints(point + 1);
                    }
                }
            }
        }
    }

    public List<Participant> scoreboard() {
        ArrayList<Participant> scoreBoard = quizzers;

        Collections.sort(scoreBoard);
        int length = 5;
        if(length > scoreBoard.size()) {
            length = scoreBoard.size();
        }

        return scoreBoard.subList(0,length);
    }

}
