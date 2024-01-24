package com.procto.Service;

import com.procto.Repository.PatientRepository;
import com.procto.entity.Patients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepo;

    public Patients createPatient(Patients patients){
        Patients savedPatient=patientRepo.save(patients);
        return savedPatient;
    }
}
