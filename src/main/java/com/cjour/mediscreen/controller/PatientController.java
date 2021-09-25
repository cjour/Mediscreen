package com.cjour.mediscreen.controller;

import com.cjour.mediscreen.model.Patient;
import com.cjour.mediscreen.service.IPatientService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Validated
public class PatientController {

    private static final Logger LOGGER = LogManager.getLogger("APPLog");

    @Autowired
    IPatientService patientService;

    @ApiOperation("Welcoming user page")
    @GetMapping("/")
    public String home() {
        return "Welcome to mediscreen";
    }

    @ApiOperation("Get a list of all patients")
    @GetMapping("/patient/list")
    public List<Patient> findAllPatient() {
        return patientService.findAll();
    }

    @ApiOperation("Get the patient associated to the id")
    @GetMapping("/patient/{id}")
    public Patient findAPatient(@PathVariable("id") Integer id) {
        return patientService.findById(id);
    }

    @ApiOperation("Insert a new patient into the database")
    @PostMapping(value = "/patient/add")
    public ResponseEntity<String> addPerson(@RequestParam @Size(min=3, max=15) String family,
                                            @RequestParam @Size(min=3, max=15) String given,
                                            @RequestParam @Past @DateTimeFormat(pattern="yyyy-MM-dd") Date dob,
                                            @RequestParam @NotBlank(message = "Sex cannot be blank") String sex,
                                            @RequestParam @NotBlank(message = "Address cannot be blank") String address,
                                            @RequestParam @NotBlank(message = "Phone cannot be blank") String phone) {

        Patient patientToRetrieve = patientService.findByFirstName(family);

        if (family == null || family.isBlank() && given == null || family.isBlank()) {
            LOGGER.error("You need at least a firstName and lastName to create a patient");
            return new ResponseEntity<>("You need at least a firstName and lastName to create a patient", HttpStatus.BAD_REQUEST);
        }

        if (patientToRetrieve!= null && patientToRetrieve.getName().equals(given)) {
            LOGGER.error("Person already exists !");
            return new ResponseEntity<>("Person already exists !", HttpStatus.CONFLICT);
        }

        Patient patientToSave = new Patient(family, given, dob, sex, address, phone);
        patientService.save(patientToSave);
        LOGGER.info("Person has been created");
        return new ResponseEntity<>("Person has been created", HttpStatus.CREATED);
    }

    @ApiOperation("Update the patient associated with the id")
    @PutMapping("/patient/update/{id}")
    public ResponseEntity<String> updateAPatient(@PathVariable("id") Integer id,
                                                 @RequestParam @Size(min=3, max=15) String family,
                                                 @RequestParam @Size(min=3, max=15) String given,
                                                 @RequestParam @Past @DateTimeFormat(pattern="yyyy-MM-dd") Date dob,
                                                 @RequestParam @Size(min=1) String sex,
                                                 @RequestParam @NotBlank(message = "Address cannot be blank") String address,
                                                 @RequestParam @NotBlank(message = "Phone cannot be blank") String phone) {

        Patient patientToRetrieve = patientService.findById(id);

        if (patientToRetrieve == null) {
            return new ResponseEntity<>("You are trying to update a non existing person", HttpStatus.OK);
        }

        patientToRetrieve.setFirstname(family);
        patientToRetrieve.setName(given);
        patientToRetrieve.setGenre(sex);
        patientToRetrieve.setBirthdate(dob);
        patientToRetrieve.setAddress(address);
        patientToRetrieve.setPhoneNumber(phone);

        patientService.save(patientToRetrieve);
        return new ResponseEntity<>("Person has been updated", HttpStatus.OK);
    }

    @ApiOperation("Delete the patient associated to the id")
    @DeleteMapping("/patient/delete/{id}")
    public ResponseEntity<String> deleteAPatient(@PathVariable("id") Integer id) {
        patientService.delete(id);
        return new ResponseEntity<>("Person has been deleted", HttpStatus.NO_CONTENT);
    }
}
