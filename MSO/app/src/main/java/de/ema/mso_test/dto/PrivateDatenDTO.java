package de.ema.mso_test.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PrivateDatenDTO implements Serializable {

    private List<NotfallkontaktDTO> notfallkontakte;
    private List<RelevanteDatenDTO> relevanteDaten;// Freitext Hund usw.
    private TestamentDTO testament;

    public List<NotfallkontaktDTO> getNotfallkontakte() {
        if (notfallkontakte == null){
            notfallkontakte= new ArrayList<>();

        }
        return notfallkontakte;
    }

    public void setNotfallkontakte(List<NotfallkontaktDTO> notfallkontakte) {
        this.notfallkontakte = notfallkontakte;
    }

    public List<RelevanteDatenDTO> getRelevanteDaten() {
        if (relevanteDaten== null){
            relevanteDaten = new ArrayList<>();
        }
        return relevanteDaten;
    }

    public void setRelevanteDaten(List<RelevanteDatenDTO> relevanteDaten) {
        this.relevanteDaten = relevanteDaten;
    }

    public TestamentDTO getTestament() {
        return testament;
    }

    public void setTestament(TestamentDTO testament) {
        this.testament = testament;
    }
}
