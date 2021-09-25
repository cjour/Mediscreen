package com.cjour.mediscreen.service;

import com.cjour.mediscreen.model.Patient;
import com.cjour.mediscreen.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements IPatientService {

    @Autowired
    PatientRepository patientRepository;

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient findById(Integer id) {
        Optional<Patient> patient = patientRepository.findById(id);
        return patient.orElse(null);    }

    @Override
    public void delete(Integer id) {
        Patient patient = findById(id);
        if (patient != null) {
            patientRepository.delete(patient);
        }
    }

    @Override
    public Patient findByFirstName(String firstname) {
        return patientRepository.findByFirstname(firstname);
    }
}
