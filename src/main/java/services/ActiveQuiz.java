package services;

import java.util.HashMap;

/**
 * Created by Camilla Velvin on 14.09.2017.
 */
public class ActiveQuiz {
    private Quiz quiz;
    HashMap<String, Integer> quizzers = new HashMap<String, Integer>(); //nickname with points
    private int spmnr = 0;
    private int antspm;

    public ActiveQuiz(Quiz quiz, String nickname) {
        this.quiz = quiz;
        quizzers.put(nickname, 0);
        antspm = quiz.getQuestion().length;
    }

    public void addParticipant(String nickname) {
        quizzers.put(nickname, 0);
    }

    public void givePoints(String nickname, int answer) {
        if(quizzers.containsKey(nickname)) {
            int[] correctAnswers = quiz.getQuestion()[spmnr].getCorrect();
            for(int i=0; i<correctAnswers.length; i++) {
                if(correctAnswers[i] == answer){
                    int points = quizzers.get(nickname);
                    quizzers.put(nickname, points++);
                }
            }
        }
    }
    public void nesteSpørsmål() {
        if(spmnr < antspm) {
            spmnr++;
        }
    }

}
