package de.hsma.stayingalive.dto.factory;

import de.hsma.stayingalive.dto.MedikamentDTO;

public class MedikamentDTOFactory {
    public static MedikamentDTO createInstance() {

        MedikamentDTO result = new MedikamentDTO();
        result.setArzt(NotfallkontaktDTOFactory.createInstance());
        return result;
    }
}
