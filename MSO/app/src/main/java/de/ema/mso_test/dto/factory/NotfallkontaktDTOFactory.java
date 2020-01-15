package de.ema.mso_test.dto.factory;

import de.ema.mso_test.dto.AnschriftDTO;
import de.ema.mso_test.dto.NotfallkontaktDTO;

public class NotfallkontaktDTOFactory {
    public static NotfallkontaktDTO createInstance() {

        NotfallkontaktDTO result = new NotfallkontaktDTO();
        result.setKontaktdaten(KontaktdatenDTOFactory.createInstance());
        result.setAnschrift(AnschriftDTOFactory.createInstance());

        return result;
    }
}
