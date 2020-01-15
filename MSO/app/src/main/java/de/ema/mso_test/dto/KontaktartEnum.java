package de.ema.mso_test.dto;

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
