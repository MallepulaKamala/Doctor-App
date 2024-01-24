package com.procto.Service;

import com.procto.Repository.DoctorRepository;
import com.procto.Repository.PatientRepository;
import com.procto.Repository.ReviewRepository;
import com.procto.entity.Doctor;
import com.procto.entity.Patients;
import com.procto.entity.Reviews;
import com.procto.payload.DoctorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private DoctorRepository doctorRepo;
    private PatientRepository patientRepo;
    private ReviewRepository reviewRepo;

    public ReviewService(DoctorRepository doctorRepo, PatientRepository patientRepo, ReviewRepository reviewRepo) {
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
        this.reviewRepo = reviewRepo;
    }
    public Reviews createReviews(Reviews reviews){
        Doctor doctorId = doctorRepo.findById(reviews.getDoctorId()).get();
        Patients patientId= patientRepo.findById(reviews.getPatientId()).get();
        Reviews savedReview=null;
        if(doctorId != null || patientId != null){
            savedReview = reviewRepo.save(reviews);
        }
        return savedReview;
    }
    public List<Reviews> getReviewsByDoctorId(long doctorId){
        List<Reviews> review = reviewRepo.findByDoctorId(doctorId);

        return review;

    }

}
