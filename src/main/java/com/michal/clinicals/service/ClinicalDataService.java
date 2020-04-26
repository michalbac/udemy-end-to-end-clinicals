package com.michal.clinicals.service;

import com.michal.clinicals.entities.ClinicalData;
import com.michal.clinicals.entities.Patient;

import java.util.List;

public interface ClinicalDataService {
    ClinicalData saveData(ClinicalData data);

    ClinicalData updateData(ClinicalData data);

    void deleteData(int id);

    ClinicalData getDataById(int id);

    List<ClinicalData> getAllData();

    List<ClinicalData> getDataForAnalyze(Patient patient);

    List<ClinicalData> getDataByPatientIdAndComponentName(int id, String componentName);

}
