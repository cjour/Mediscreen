package com.cjour.mediscreen.service;

import com.cjour.mediscreen.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPatientService {

    List<Patient> findAll();

    Patient save(Patient patient);

    Patient findById(Integer id);

    void delete(Integer id);

    Patient findByFirstName(String firstname);

}
