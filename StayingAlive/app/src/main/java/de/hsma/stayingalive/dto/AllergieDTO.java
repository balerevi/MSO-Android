package de.hsma.stayingalive.dto;

import java.io.Serializable;
import java.util.List;

public class AllergieDTO implements Serializable {
    private String allergie;
    private List<String> informationen;

    public String getAllergie() {
        return allergie;
    }

    public void setAllergie(String allergie) {
        this.allergie = allergie;
    }

    public List<String> getInformationen() {
        return informationen;
    }

    public void setInformationen(List<String> informationen) {
        this.informationen = informationen;
    }
}
