package com.michal.clinicals.controller;

import com.michal.clinicals.entities.ClinicalData;
import com.michal.clinicals.entities.Patient;
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
public class PatientController {
    @Autowired
    PatientService patientService;

    @Autowired
    ClinicalDataService clinicalDataService;


    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    public List<Patient> getPatients(){
        return patientService.getAllPatients();
    }

    @RequestMapping(value = "/patients/{id}", method = RequestMethod.GET)
    public Patient getPatient(@PathVariable int id){
        return patientService.getPatientById(id);
    }

    @RequestMapping(value = "/patients", method = RequestMethod.POST)
    public Patient savePatient(@RequestBody Patient patient){
        return patientService.savePatient(patient);
    }


    @RequestMapping(value = "/patients/analyze/{id}", method = RequestMethod.GET)
    public Patient analyze(@PathVariable int id){
        Patient patient = patientService.getPatientById(id);
        List<ClinicalData> clinicalData = clinicalDataService.getDataForAnalyze(patient);
        List<ClinicalData> duplicatedData = new ArrayList<>(clinicalData);
        duplicatedData.stream()
                .filter(c -> c.getComponentName().equals("hw"))
                .forEach(c ->
                        {ClinicalData bmi = new ClinicalData();
                        bmi.setComponentName("bmi");
                        bmi.setPatient(patient);
                        bmi.setComponentValue(ClinicalDataUtil.calculateBMI(c.getComponentValue()).toString());
                        clinicalData.add(bmi);});
        return patient;
    }


}
