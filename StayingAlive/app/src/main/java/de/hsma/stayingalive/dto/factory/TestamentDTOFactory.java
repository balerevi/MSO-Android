package de.hsma.stayingalive.dto.factory;

import de.hsma.stayingalive.dto.TestamentDTO;

class TestamentDTOFactory {
    public static TestamentDTO createInstance() {
        TestamentDTO  result = new TestamentDTO();
        result.setNotar(NotfallkontaktDTOFactory.createInstance());

        return result;

    }
}
