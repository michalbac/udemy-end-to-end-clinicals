package com.michal.clinicals.util;

public class ClinicalDataUtil {

    public static Double calculateBMI(String hw){
        String [] data = hw.split("/");
        if (data[0]!= null && data[1]!=null){
            double heightInMeters = Double.parseDouble(data[0]) * 0.3048D;
            double weightInKg = Double.parseDouble(data[1]) * 0.45359237D;
            double bmi = Math.round(weightInKg/(Math.pow(heightInMeters, 2))*100D)/100D;
            return  bmi;
        }
        return null;
    }
}
