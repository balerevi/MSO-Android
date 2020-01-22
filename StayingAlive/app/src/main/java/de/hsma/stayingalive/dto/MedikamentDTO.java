package de.hsma.stayingalive.dto;

import java.io.Serializable;

/**
 * Repräsentiert ein Medikament, enthält Informationen über Art, Dauer, Doszierung, usw.
 */
public class MedikamentDTO implements Serializable {

    private String name;
    private String dosierung;
    private NotfallkontaktDTO arzt;
    private String informationen;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosierung() {
        return dosierung;
    }

    public void setDosierung(String dosierung) {
        this.dosierung = dosierung;
    }

    public NotfallkontaktDTO getArzt() {
        return arzt;
    }

    public void setArzt(NotfallkontaktDTO arzt) {
        this.arzt = arzt;
    }

    public String getInformationen() {
        return informationen;
    }

    public void setInformationen(String informationen) {
        this.informationen = informationen;
    }
}
