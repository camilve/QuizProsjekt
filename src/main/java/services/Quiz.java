package services;

import java.util.Date;

/**
 * Created by Camilla Velvin on 14.09.2017.
 */
public class Quiz {
    private Spørsmål[] spørsmål;
    private Date tid;

    public Quiz(Spørsmål[] spørsmål, Date tid) {
        this.spørsmål = spørsmål;
        this.tid = tid;
    }
    public Spørsmål[] getSpørsmål() {
        return spørsmål;
    }
    public Date getTid() {
        return tid;
    }
}
