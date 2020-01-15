package de.ema.mso_test.dto.factory;

import de.ema.mso_test.dto.PrivateDatenDTO;

class PrivateDatenDTOFactory {
    public static PrivateDatenDTO createInstance() {

        PrivateDatenDTO result = new PrivateDatenDTO();
        result.setTestament(TestamentDTOFactory.createInstance());
        return result;
    }
}
