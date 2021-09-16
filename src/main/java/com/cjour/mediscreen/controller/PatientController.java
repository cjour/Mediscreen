package com.cjour.mediscreen.controller;

import com.cjour.mediscreen.model.Patient;
import com.cjour.mediscreen.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientController {

    @Autowired
    PatientService patientService;

    @RequestMapping("patient/list")
    public List<Patient> findAllPatient() {
        return patientService.findAll();
    }

    @PostMapping("patient/add/{patient}")
    public Patient findAPatient(@PathVariable("patient") Patient patient) {
        return patientService.save(patient);
    }

}
