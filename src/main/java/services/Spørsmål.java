package services;

/**
 * Created by Camilla Velvin on 14.09.2017.
 */
public class Spørsmål {
    private String spørsmål;
    private String[] svar;
    private int riktig;
    private String url;
    double seconds;

    public Spørsmål(String spørsmål, String[] svar, int riktig, String url, double seconds) {
        this.spørsmål = spørsmål;
        this.svar = svar;
        this.riktig = riktig;
        this.url = url;
        this.seconds = seconds;
    }

    public int getRiktig() {
        return riktig;
    }

    public void setRiktig(int riktig) {
        this.riktig = riktig;
    }

    public String getSpørsmål() {
        return spørsmål;
    }

    public void setSpørsmål(String spørsmål) {
        this.spørsmål = spørsmål;
    }

    public String[] getSvar() {
        return svar;
    }

    public void setSvar(String[] svar) {
        this.svar = svar;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getSeconds() {
        return seconds;
    }

    public void setSeconds(double seconds) {
        this.seconds = seconds;
    }
}
