package de.ema.mso_test.dto.factory;

import de.ema.mso_test.dto.MedizinischeInformationenDTO;

class MedizinischeInformationenDTOFactory {


    static MedizinischeInformationenDTO createInstance() {
        MedizinischeInformationenDTO result = new MedizinischeInformationenDTO();
        result.setOrganspender(OrganspenderDTOFactory.createInstance());
        result.setPatientenverfuegung(PatientenverfuegungDTOFactory.createInstance());

        return result;
    }
}
