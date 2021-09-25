package com.cjour.mediscreen.service;

import com.cjour.mediscreen.model.Patient;
import com.cjour.mediscreen.repository.PatientRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PatientServiceTests {

    @Autowired
    PatientService patientService;

    private Patient patient;

    @BeforeEach
    public void setUp() {
        this.patient = new Patient("Clément", "JOURDAIN", new Date(), "Homme", "address", "007");
        patientService.save(patient);
    }

    @AfterEach
    public void tearDown() {
        List<Patient> listOfPatient = patientService.findAll();
        for (Patient patient : listOfPatient) {
            patientService.delete(patient.getId());
        }
    }

    @Test
    public void should_find_patient_by_id_in_DB() {
        Patient patientFromDB = patientService.findById(patient.getId());
        Assertions.assertEquals(patient.getId(), patientFromDB.getId());
    }

    @Test
    public void should_findAll_patient_from_DB() {
        List<Patient> listOfPatient = patientService.findAll();
        Assertions.assertEquals(listOfPatient.size(), 1);
    }

    @Test
    public void should_find_patient_by_firstname() {
        Patient patient = patientService.findByFirstName(this.patient.getFirstname());
        Assertions.assertEquals(patient.getFirstname(), this.patient.getFirstname());
    }
}
