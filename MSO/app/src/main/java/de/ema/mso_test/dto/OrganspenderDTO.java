package de.ema.mso_test.dto;

import java.io.Serializable;

public class OrganspenderDTO implements Serializable {

    private boolean istOrganspender;
    private String information;

    public boolean isIstOrganspender() {
        return istOrganspender;
    }

    public void setIstOrganspender(boolean istOrganspender) {
        this.istOrganspender = istOrganspender;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
