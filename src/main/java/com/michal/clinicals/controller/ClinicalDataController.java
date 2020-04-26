package com.michal.clinicals.controller;

import com.michal.clinicals.dto.ClinicalDataRequest;
import com.michal.clinicals.entities.ClinicalData;
import com.michal.clinicals.mapper.ClinicalDataMapper;
import com.michal.clinicals.service.ClinicalDataService;
import com.michal.clinicals.service.PatientService;
import com.michal.clinicals.util.ClinicalDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ClinicalDataController {
    @Autowired
    PatientService patientService;

    @Autowired
    ClinicalDataService clinicalDataService;


    @Autowired
    ClinicalDataMapper mapper;

    @RequestMapping(value = "/clinicals", method = RequestMethod.POST)
    public ClinicalData saveClinicalData(@RequestBody ClinicalDataRequest request){
        ClinicalData data = mapper.mapToData(request);
        return clinicalDataService.saveData(data);
    }

    @RequestMapping(value = "/clinicals/{patientId}/{componentName}", method = RequestMethod.GET)
    public List<ClinicalData> getClinicalData(@PathVariable int patientId, @PathVariable String componentName){
        if (componentName.equals("bmi")){
            componentName = "hw";
        }
        List<ClinicalData> data = clinicalDataService.getDataByPatientIdAndComponentName(patientId, componentName);
        List<ClinicalData> duplicatedData = new ArrayList<>(data);
        duplicatedData.stream()
                .filter(c -> c.getComponentName().equals("hw"))
                .forEach(c ->
                {ClinicalData bmi = new ClinicalData();
                    bmi.setComponentName("bmi");
                    bmi.setPatient(patientService.getPatientById(patientId));
                    bmi.setComponentValue(ClinicalDataUtil.calculateBMI(c.getComponentValue()).toString());
                    data.add(bmi);});
        return data;
    }
}
