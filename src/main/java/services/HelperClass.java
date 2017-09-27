package services;

/**
 * Created by Camilla Velvin on 24.09.2017.
 */
public class HelperClass {
    private String nickname;
    private String id;
    private int answer;
    private int questionNumber;
    public HelperClass() {
    }

    public HelperClass(String nickname, String id, int answer, int questionNumber) {
        this.nickname = nickname;
        this.id = id;
        this.answer = answer;
        this.questionNumber = questionNumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }
}
