package com.cjour.mediscreen.controller;

import com.cjour.mediscreen.model.Patient;
import com.cjour.mediscreen.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping("patient/list")
    public List<Patient> findAllPatient() {
        return patientService.findAll();
    }

    @GetMapping("patient/{id}")
    public Patient findAPatient(@PathVariable("id") Integer id) {
        return patientService.findById(id);
    }

}
