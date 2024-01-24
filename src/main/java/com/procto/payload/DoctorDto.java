package com.procto.payload;

import com.procto.entity.Doctor;
import com.procto.entity.Reviews;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {
    private Doctor doctor;
    private List<Reviews> reviews;
    private double ratingPercentage;
}
