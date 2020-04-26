package com.michal.clinicals.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicalDataRequest {
    private String componentName;
    private String componentValue;
    private int patientId;
}
