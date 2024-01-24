package com.procto.Controller;

import com.procto.Service.PatientService;
import com.procto.entity.Patients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;
    @PostMapping
    public ResponseEntity<Patients> createPatient(@RequestBody Patients patients){
        return new ResponseEntity<Patients>(patientService.createPatient(patients), HttpStatus.CREATED);
    }
}
