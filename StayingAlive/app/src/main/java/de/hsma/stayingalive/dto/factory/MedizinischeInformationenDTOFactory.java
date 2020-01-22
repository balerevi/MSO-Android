package de.hsma.stayingalive.dto.factory;

import de.hsma.stayingalive.dto.MedizinischeInformationenDTO;

class MedizinischeInformationenDTOFactory {


    static MedizinischeInformationenDTO createInstance() {
        MedizinischeInformationenDTO result = new MedizinischeInformationenDTO();
        result.setOrganspender(OrganspenderDTOFactory.createInstance());
        result.setPatientenverfuegung(PatientenverfuegungDTOFactory.createInstance());

        return result;
    }
}
