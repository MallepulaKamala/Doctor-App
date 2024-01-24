package com.procto.Repository;

import com.procto.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
   public boolean existsByDoctorIdAndPatientIdAndBookingTime(long doctorId, long patientId, String requestedTimeSlot);
}
