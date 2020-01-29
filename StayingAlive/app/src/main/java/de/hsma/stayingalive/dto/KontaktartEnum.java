package de.hsma.stayingalive.dto;

import java.io.Serializable;

public enum KontaktartEnum implements Serializable {
    ARBEITGEBER ("Arbeitgeber"),
    FREUND ("Freund"),
    BEKANNTER ("Bekannter"),
    EHEGATTE ("Ehegatte"),
    LEBENSPARTNER ("Lebenspartner"),
    KIND ("Kind"),
    FAMILIE ("Familie");

    private String value;

    private KontaktartEnum(String value){
        this.value = value;
    }

    public static String[] valuesAsString() {
        KontaktartEnum[] values = KontaktartEnum.values();
        String[] asString = new String[values.length];

        for(int i = 0; i < values.length; i++)
            asString[i] = values[i].value;

        return asString;
    }


    public static KontaktartEnum findByValue(String value){
        for (KontaktartEnum kontaktart : values()){
            if (kontaktart.getValue().equals(value)){
            return kontaktart;
            }
        }
        return null;
    }
    public String getValue() {
        return value;
    }
}
