package de.hsma.stayingalive.manager;

import de.hsma.stayingalive.dto.NutzerDTO;

public class NutzerDTOManager  {

    private NutzerDTO nutzerDto;
    private static NutzerDTOManager instance = null;

    private NutzerDTOManager() {
    }

    public static NutzerDTOManager getInstance(){
        if (instance==null){
            instance = new NutzerDTOManager();
        }
        return instance;
    }

    public NutzerDTO getNutzerDto() {
        return nutzerDto;
    }

    public void setNutzerDto(NutzerDTO nutzerDto) {
        this.nutzerDto = nutzerDto;
    }
}
