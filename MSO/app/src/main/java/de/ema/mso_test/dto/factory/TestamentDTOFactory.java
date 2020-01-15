package de.ema.mso_test.dto.factory;

import de.ema.mso_test.dto.TestamentDTO;

class TestamentDTOFactory {
    public static TestamentDTO createInstance() {
        TestamentDTO  result = new TestamentDTO();
        result.setNotar(NotfallkontaktDTOFactory.createInstance());

        return result;

    }
}
