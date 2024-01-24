package com.procto.Controller;

import com.procto.Repository.DoctorRepository;
import com.procto.Service.ReviewService;
import com.procto.entity.Doctor;
import com.procto.entity.Reviews;
import com.procto.payload.DoctorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private ReviewService reviewService;
    private DoctorRepository doctorRepo;

    @Autowired
    public void setReviewService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Autowired
    public void setDoctorRepo(DoctorRepository doctorRepo) {
        this.doctorRepo = doctorRepo;
    }





    @PostMapping
    public ResponseEntity<Reviews> createReviews(@RequestBody Reviews reviews){
        Reviews reviews1 = reviewService.createReviews(reviews);
        return new ResponseEntity<>(reviews1, HttpStatus.CREATED);

    }
    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorDto> getReviewsByDoctorId(@PathVariable long doctorId){
        Doctor doctorIds = doctorRepo.findById(doctorId).get();
        List<Reviews> reviewByDoctorId = reviewService.getReviewsByDoctorId(doctorId);
        double totalRating = 0;
        for (Reviews review : reviewByDoctorId) {
            totalRating += review.getRating();
        }
        double averageRating = (double) totalRating /reviewByDoctorId.size();
        double ratingPercentage= (averageRating / 5.0) * 100.0;

        DoctorDto dto=new DoctorDto();
        dto.setDoctor(doctorIds);
        dto.setReviews(reviewByDoctorId);
        dto.setRatingPercentage(ratingPercentage);
        return new ResponseEntity(dto, HttpStatus.OK);


    }




}
