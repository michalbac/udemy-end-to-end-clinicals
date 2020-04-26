package com.michal.clinicals.service;

import com.michal.clinicals.entities.Patient;

import java.util.List;

public interface PatientService {
    Patient savePatient(Patient patient);

    Patient updatePatient(Patient patient);

    void deletePatient(int id);

    Patient getPatientById(int id);

    List<Patient> getAllPatients();

}
