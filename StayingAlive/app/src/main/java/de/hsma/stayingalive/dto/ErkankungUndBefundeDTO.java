package de.hsma.stayingalive.dto;

import java.io.Serializable;
import java.util.List;

public class ErkankungUndBefundeDTO implements Serializable {
    private String name;
    private List<String> informationen;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getInformationen() {
        return informationen;
    }

    public void setInformationen(List<String> informationen) {
        this.informationen = informationen;
    }
}
