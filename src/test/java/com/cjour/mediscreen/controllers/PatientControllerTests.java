package com.cjour.mediscreen.controllers;

import com.cjour.mediscreen.controller.PatientController;
import com.cjour.mediscreen.model.Patient;
import com.cjour.mediscreen.service.PatientService;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PatientController.class)
public class PatientControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    private List<Patient> listOfPatient;

    private static Patient patient;

    @BeforeAll
    static void setUp() {
        patient = new Patient();
        List<Patient> listOfPatient = new ArrayList<>();
        listOfPatient.add(patient);
    }

    @Test
    public void should_return_list_of_patient() throws Exception {
        Mockito.when(patientService.findAll()).thenReturn(listOfPatient);
        mockMvc.perform(get("/patient/list")).andExpect(status().isOk());
    }

    @Test
    public void should_return_isOk_on_get_patient_by_id() throws Exception {
        Mockito.when(patientService.findById(anyInt())).thenReturn(patient);
        mockMvc.perform(get("/patient/{id}", 1)).andExpect(status().isOk());
    }

    @Test
    public void should_return_isCreated_on_add_patient() throws Exception {
        Mockito.when(patientService.save(patient)).thenReturn(patient);
        mockMvc.perform(post("/patient/add")
                    .param("family", "JOURDAIN")
                    .param("given", "Clément")
                    .param("dob", "1992-05-17")
                    .param("sex", "Homme")
                    .param("address", "address")
                    .param("phone", "NA"))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_return_isNoContent_on_delete_patient() throws Exception {
        Mockito.when(patientService.findById(anyInt())).thenReturn(null);
        mockMvc.perform(delete("/patient/delete/{id}", 1)).andExpect(status().isNoContent());
    }

    @Test
    public void should_return_isOk_on_update_patient() throws Exception {
        Mockito.when(patientService.findById(anyInt())).thenReturn(patient);
        mockMvc.perform(put("/patient/update/{id}", 1)
                .param("family", "JOURDAIN")
                .param("given", "Clément")
                .param("dob", "1992-05-17")
                .param("sex", "Homme")
                .param("address", "address")
                .param("phone", "NA"))
                .andExpect(status().isOk());
    }

    //TODO:Verify how to test this case.
    /*@Test
    public void should_return_isNotValid_on_add_patient() throws Exception {
        Mockito.when(patientService.save(patient)).thenReturn(patient);
        mockMvc.perform(post("/patient/add")
                .param("family", "JO")
                .param("given", "Clément")
                .param("dob", "1992-05-17")
                .param("sex", "Homme")
                .param("address", "address")
                .param("phone", "NA"))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof javax.validation.ConstraintViolationException));
    }*/
}
