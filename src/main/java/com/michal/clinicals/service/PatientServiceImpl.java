package com.michal.clinicals.service;

import com.michal.clinicals.entities.Patient;
import com.michal.clinicals.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientRepository patientRepository;

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(int id) {
        patientRepository.deleteById(id);
    }

    @Override
    public Patient getPatientById(int id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()){
            return patient.get();
        }
        return null;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
}
