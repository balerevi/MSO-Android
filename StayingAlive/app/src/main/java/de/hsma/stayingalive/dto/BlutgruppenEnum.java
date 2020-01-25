package de.hsma.stayingalive.dto;

import java.io.Serializable;

public enum BlutgruppenEnum implements Serializable {

    UNBEKANNT("-"),
    NULL_POSITIV("0+"),
    NULL_NEGATIV("0-"),
    A_POSITIV("A+"),
    A_NEGATIV("A-"),
    B_POSITIV("B+"),
    B_NEGATIV("B-"),
    AB_POSITIV("AB+"),
    AB_NEGATIV("AB-");

    private final String text;

    private BlutgruppenEnum(String text) {
        this.text = text;
    }

    public static String[] valuesAsString() {
        BlutgruppenEnum[] values = BlutgruppenEnum.values();
        String[] asString = new String[values.length];

        for(int i = 0; i < values.length; i++)
            asString[i] = values[i].text;

        return asString;
    }

}
