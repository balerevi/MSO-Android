package de.hsma.stayingalive.dto.factory;

import java.util.UUID;

import de.hsma.stayingalive.dto.NutzerDTO;


/**
 * Erzeugt eine neue Instanz von NutzerDTO
 */
public class NutzerDTOFactory {

    public static NutzerDTO createInstance(){
        NutzerDTO result = new NutzerDTO();
        result.setId(null);
        result.setMedizinischeInformationen(MedizinischeInformationenDTOFactory.createInstance());
        result.setPersoenlicheDaten(PersoenlicheDatenDTOFactory.createInstance());
        result.setPrivateDaten(PrivateDatenDTOFactory.createInstance());

        return result;
    }

}
