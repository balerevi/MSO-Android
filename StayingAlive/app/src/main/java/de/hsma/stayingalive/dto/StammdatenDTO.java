package de.hsma.stayingalive.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.io.Serializable;
import java.time.LocalDate;

public class StammdatenDTO implements Serializable {

    private KontaktdatenDTO kontaktdaten;
    private AnschriftDTO anschrift;
    private String vorname;
    private String name;
    private LocalDate start;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate geburtsdatum;
    private String beruf;
    private String gewicht;
    private String groesse;



    public KontaktdatenDTO getKontaktdaten() {
        return kontaktdaten;
    }

    public void setKontaktdaten(KontaktdatenDTO kontaktdaten) {
        this.kontaktdaten = kontaktdaten;
    }

    public AnschriftDTO getAnschrift() {
        return anschrift;
    }

    public void setAnschrift(AnschriftDTO anschrift) {
        this.anschrift = anschrift;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getBeruf() {
        return beruf;
    }

    public void setBeruf(String beruf) {
        this.beruf = beruf;
    }

    public String getGewicht() {
        return gewicht;
    }

    public void setGewicht(String gewicht) {
        this.gewicht = gewicht;
    }

    public String getGroesse() {
        return groesse;
    }

    public void setGroesse(String groesse) {
        this.groesse = groesse;
    }
}
