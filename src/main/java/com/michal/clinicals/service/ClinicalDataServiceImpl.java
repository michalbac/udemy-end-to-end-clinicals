package com.michal.clinicals.service;

import com.michal.clinicals.entities.ClinicalData;
import com.michal.clinicals.entities.Patient;
import com.michal.clinicals.repository.ClinicalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClinicalDataServiceImpl implements ClinicalDataService{

    @Autowired
    ClinicalDataRepository clinicalDataRepository;


    @Override
    public ClinicalData saveData(ClinicalData data) {
        return clinicalDataRepository.save(data);
    }

    @Override
    public ClinicalData updateData(ClinicalData data) {
        return clinicalDataRepository.save(data);
    }

    @Override
    public void deleteData(int id) {
        clinicalDataRepository.deleteById(id);
    }

    @Override
    public ClinicalData getDataById(int id) {
         Optional<ClinicalData> data = clinicalDataRepository.findById(id);
        if (data.isPresent()){
            return data.get();
        }
        return null;
    }

    @Override
    public List<ClinicalData> getAllData() {
        return clinicalDataRepository.findAll();
    }

    @Override
    public List<ClinicalData> getDataForAnalyze(Patient patient) {
        Map<String, String> filteredData = new HashMap<>();
        List<ClinicalData> data = patient.getClinicalData();
        List<ClinicalData> duplicatedData = new ArrayList<>(data);
        for (ClinicalData clinicalData : duplicatedData){
            if (filteredData.containsKey(clinicalData.getComponentName())){
                data.remove(clinicalData);
            } else {
                filteredData.put(clinicalData.getComponentName(),clinicalData.getComponentValue());
            }
        }

        return data;
    }

    @Override
    public List<ClinicalData> getDataByPatientIdAndComponentName(int id, String componentName) {
        return clinicalDataRepository.findByPatientIdAndComponentNameOrderByMeasuredDateTime(id, componentName);
    }
}
