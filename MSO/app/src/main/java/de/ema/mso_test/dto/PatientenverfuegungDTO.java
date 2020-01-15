package de.ema.mso_test.dto;

import java.io.Serializable;

public class PatientenverfuegungDTO implements Serializable {

    private boolean hatPatientenverfuegung;
    private String information;

    public boolean isHatPatientenverfuegung() {
        return hatPatientenverfuegung;
    }

    public void setHatPatientenverfuegung(boolean hatPatientenverfuegung) {
        this.hatPatientenverfuegung = hatPatientenverfuegung;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
