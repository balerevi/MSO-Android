package de.hsma.stayingalive.dto.factory;

import de.hsma.stayingalive.dto.PrivateDatenDTO;

class PrivateDatenDTOFactory {
    public static PrivateDatenDTO createInstance() {

        PrivateDatenDTO result = new PrivateDatenDTO();
        result.setTestament(TestamentDTOFactory.createInstance());
        return result;
    }
}
