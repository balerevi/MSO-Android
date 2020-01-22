package de.hsma.stayingalive.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Kapselt die Medizinischen Informationen zu einer Person
 */
public class MedizinischeInformationenDTO  implements Serializable {

    private BlutgruppenEnum blutgruppe;
    private List<MedikamentDTO> medikamente;
    private List<AllergieDTO> allergien;
    private List<ErkankungUndBefundeDTO> erkankungUndBefunde;
    private String informationen;
    private PatientenverfuegungDTO patientenverfuegung;
    private OrganspenderDTO organspender;

    public BlutgruppenEnum getBlutgruppe() {
        return blutgruppe;
    }

    public void setBlutgruppe(BlutgruppenEnum blutgruppe) {
        this.blutgruppe = blutgruppe;
    }

    public List<MedikamentDTO> getMedikamente() {
        if (medikamente== null){
            medikamente = new ArrayList<>();
        }
        return medikamente;
    }

    public void setMedikamente(List<MedikamentDTO> medikamente) {
        this.medikamente = medikamente;
    }

    public List<AllergieDTO> getAllergien() {
        if (allergien== null){
            allergien= new ArrayList<>();
        }
        return allergien;
    }

    public void setAllergien(List<AllergieDTO> allergien) {
        this.allergien = allergien;
    }

    public List<ErkankungUndBefundeDTO> getErkankungUndBefunde() {
        if (erkankungUndBefunde== null){
            erkankungUndBefunde = new ArrayList<>();
        }
        return erkankungUndBefunde;
    }

    public void setErkankungUndBefunde(List<ErkankungUndBefundeDTO> erkankungUndBefunde) {
        this.erkankungUndBefunde = erkankungUndBefunde;
    }

    public String getInformationen() {
        return informationen;
    }

    public void setInformationen(String informationen) {
        this.informationen = informationen;
    }

    public PatientenverfuegungDTO getPatientenverfuegung() {
        return patientenverfuegung;
    }

    public void setPatientenverfuegung(PatientenverfuegungDTO patientenverfuegung) {
        this.patientenverfuegung = patientenverfuegung;
    }

    public OrganspenderDTO getOrganspender() {
        return organspender;
    }

    public void setOrganspender(OrganspenderDTO organspender) {
        this.organspender = organspender;
    }
}


