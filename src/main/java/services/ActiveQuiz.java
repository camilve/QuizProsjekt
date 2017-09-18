package services;

import java.util.HashMap;

/**
 * Created by Camilla Velvin on 14.09.2017.
 */
public class ActiveQuiz {
    private Quiz quiz;
    HashMap<String, Integer> quizzers = new HashMap<String, Integer>();
    private int spmnr = 0;
    private int antspm;

    public ActiveQuiz(Quiz quiz, String nickname) {
        this.quiz = quiz;
        quizzers.put(nickname, 0);
        antspm = quiz.getSpørsmål().length;
    }

    public void addDeltaker(String nickname) {
        quizzers.put(nickname, 0);
    }

    public void giPoeng(String nickname, int svar) {
        if(quizzers.containsKey(nickname)) {
            if(quiz.getSpørsmål()[spmnr].getRiktig() == svar){
                int antPoeng = quizzers.get(nickname);
                quizzers.put(nickname, antPoeng++);
            }
        }
    }
    public void nesteSpørsmål() {
        if(spmnr < antspm) {
            spmnr++;
        }
    }

}
