package entities;

/**
 * Created by Camilla Velvin on 14.09.2017.
 */
public class Question {
    private String question;
    private String[] answers;
    private int[] correct;
    private String url;
    private int seconds;

    public Question() {}
    public Question(String question, String[] answers, int[] correct, String url, int seconds) {
        this.question = question;
        this.answers = answers;
        this.correct = correct;
        this.url = url;
        this.seconds = seconds;
    }

    public int[] getCorrect() {
        return correct;
    }

    public void setCorrect(int[] correct) {
        this.correct = correct;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String spørsmål) {
        this.question = spørsmål;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] svar) {
        this.answers = svar;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
