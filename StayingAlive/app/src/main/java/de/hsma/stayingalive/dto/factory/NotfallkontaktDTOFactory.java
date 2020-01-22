package de.hsma.stayingalive.dto.factory;

import de.hsma.stayingalive.dto.NotfallkontaktDTO;

public class NotfallkontaktDTOFactory {
    public static NotfallkontaktDTO createInstance() {

        NotfallkontaktDTO result = new NotfallkontaktDTO();
        result.setKontaktdaten(KontaktdatenDTOFactory.createInstance());
        result.setAnschrift(AnschriftDTOFactory.createInstance());

        return result;
    }
}
