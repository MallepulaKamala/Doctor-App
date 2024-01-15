package com.procto.Service;

import com.procto.Repository.DoctorRepository;
import com.procto.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepo;

    public Doctor addDoctor(Doctor doctor){
        return doctorRepo.save(doctor);
    }

    public List<Doctor> searchDoctorsByNameOrSpecialization(String search){
        return doctorRepo.searchByNameOrSpecialization(search);
    }
}
