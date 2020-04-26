package com.michal.clinicals.mapper;

import com.michal.clinicals.dto.ClinicalDataRequest;
import com.michal.clinicals.entities.ClinicalData;
import com.michal.clinicals.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClinicalDataMapper {
    @Autowired
    PatientService patientService;

    public ClinicalDataRequest mapToRequest(ClinicalData data){
        ClinicalDataRequest request = new ClinicalDataRequest();
        request.setComponentName(data.getComponentName());
        request.setComponentValue(data.getComponentValue());
        request.setPatientId(data.getPatient().getId());
        return request;
    }

    public ClinicalData mapToData (ClinicalDataRequest request){
        ClinicalData data = new ClinicalData();
        data.setComponentName(request.getComponentName());
        data.setComponentValue(request.getComponentValue());
        data.setPatient(patientService.getPatientById(request.getPatientId()));
        return data;
    }
}
