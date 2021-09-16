package com.cjour.mediscreen.service;

import com.cjour.mediscreen.model.Patient;

import java.util.List;

public interface IPatientService {

    public List<Patient> findAll();

    public Patient save (Patient patient);

    public Patient findById(Integer id);

    public void delete(Integer id);
}
