package de.ema.mso_test.dto.factory;

import java.util.UUID;

import de.ema.mso_test.dto.NutzerDTO;


/**
 * Erzeugt eine neue Instanz von NutzerDTO
 */
public class NutzerDTOFactory {

    public static NutzerDTO createInstance(){
        NutzerDTO result = new NutzerDTO();
        result.setId(UUID.randomUUID().toString());

        result.setMedizinischeInformationen(MedizinischeInformationenDTOFactory.createInstance());
        result.setPersoenlicheDaten(PersoenlicheDatenDTOFactory.createInstance());
        result.setPrivateDaten(PrivateDatenDTOFactory.createInstance());

        return result;
    }

}
