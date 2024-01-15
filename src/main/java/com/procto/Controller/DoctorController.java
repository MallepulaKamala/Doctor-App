package com.procto.Controller;

import com.procto.Service.DoctorService;
import com.procto.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor){
        Doctor savedDoctor=doctorService.addDoctor(doctor);
        return new ResponseEntity<Doctor>(savedDoctor, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public List<Doctor> searchDoctors(@RequestParam String search){
        return doctorService.searchDoctorsByNameOrSpecialization(search);
    }
}
