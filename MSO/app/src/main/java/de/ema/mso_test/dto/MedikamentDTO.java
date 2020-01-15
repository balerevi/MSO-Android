package de.ema.mso_test.dto;

import java.io.Serializable;

/**
 * Repräsentiert ein Medikament, enthält Informationen über Art, Dauer, Doszierung, usw.
 */
public class MedikamentDTO implements Serializable {

    private String name;
    private String dosierung;
    private NotfallkontaktDTO arzt;
    private String informationen;

}
