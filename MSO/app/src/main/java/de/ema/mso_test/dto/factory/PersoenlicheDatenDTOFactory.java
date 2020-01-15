package de.ema.mso_test.dto.factory;

import de.ema.mso_test.dto.NotfallkontaktDTO;
import de.ema.mso_test.dto.StammdatenDTO;

class PersoenlicheDatenDTOFactory {
     static StammdatenDTO createInstance() {

         StammdatenDTO result = new StammdatenDTO();
         result.setAnschrift(AnschriftDTOFactory.createInstance());
         result.setKontaktdaten(KontaktdatenDTOFactory.createInstance());

         return result;
    }
}
