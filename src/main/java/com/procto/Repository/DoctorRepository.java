package com.procto.Repository;

import com.procto.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    @Query("SELECT d FROM Doctor d WHERE d.name LIKE %:search% OR d.specialization LIKE %:search%")
    List<Doctor> searchByNameOrSpecialization(@RequestParam("search")String search);
}
