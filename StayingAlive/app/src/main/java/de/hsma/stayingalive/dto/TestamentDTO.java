package de.hsma.stayingalive.dto;

import java.io.Serializable;

public class TestamentDTO implements Serializable {

    private boolean testamentVorhanden;
    private NotfallkontaktDTO notar;
    private String informationen;

    public boolean isTestamentVorhanden() {
        return testamentVorhanden;
    }

    public void setTestamentVorhanden(boolean testamentVorhanden) {
        this.testamentVorhanden = testamentVorhanden;
    }

    public NotfallkontaktDTO getNotar() {
        return notar;
    }

    public void setNotar(NotfallkontaktDTO notar) {
        this.notar = notar;
    }

    public String getInformationen() {
        return informationen;
    }

    public void setInformationen(String informationen) {
        this.informationen = informationen;
    }
}
