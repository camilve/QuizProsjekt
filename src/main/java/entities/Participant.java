package entities;

/**
 * Created by Camilla Velvin on 20.09.2017.
 */
public class Participant implements Comparable<Participant> {
    private final String nickname;
    private int points;

    public Participant(String nickname) {
        this.nickname = nickname;
        points = 0;
    }

    public String getNickname() {
        return nickname;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int compareTo(Participant o) {
        return o.getPoints() - points;
    }
}
