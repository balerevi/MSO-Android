package de.hsma.stayingalive.dto.factory;

import de.hsma.stayingalive.dto.StammdatenDTO;

class PersoenlicheDatenDTOFactory {
     static StammdatenDTO createInstance() {

         StammdatenDTO result = new StammdatenDTO();
         result.setAnschrift(AnschriftDTOFactory.createInstance());
         result.setKontaktdaten(KontaktdatenDTOFactory.createInstance());

         return result;
    }
}
