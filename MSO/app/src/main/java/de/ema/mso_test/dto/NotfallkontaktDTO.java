package de.ema.mso_test.dto;

import java.io.Serializable;

public class NotfallkontaktDTO implements Serializable {

    private String name;
    private KontaktdatenDTO kontaktdaten;
    private KontaktartEnum kontaktart;
    private AnschriftDTO anschrift;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public KontaktdatenDTO getKontaktdaten() {
        return kontaktdaten;
    }

    public void setKontaktdaten(KontaktdatenDTO kontaktdaten) {
        this.kontaktdaten = kontaktdaten;
    }

    public KontaktartEnum getKontaktart() {
        return kontaktart;
    }

    public void setKontaktart(KontaktartEnum kontaktart) {
        this.kontaktart = kontaktart;
    }

    public AnschriftDTO getAnschrift() {
        return anschrift;
    }

    public void setAnschrift(AnschriftDTO anschrift) {
        this.anschrift = anschrift;
    }
}
