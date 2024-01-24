//package com.procto.Service;
//
//import com.procto.Config.TimeSlotManager;
//import com.procto.Repository.BookingRepository;
//import com.procto.entity.Booking;
//import com.procto.payload.BookingDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//import java.util.List;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//@Service
//public class BookingService {
//    @Autowired
//    private BookingRepository bookingRepo;
//
//    @Autowired
//    private TimeSlotManager timeSlotManager;
//
//    public void bookAnAppointment(BookingDto bookingDto) {
//        List<String> availableTimeSlots=timeSlotManager.getAvailableTimeSlots();
//
//        Booking booking = new Booking();
//
//        for (String slots : availableTimeSlots) {
//            if (slots.equals(bookingDto.getBookingTime())) {
//                booking.setBookingTime(bookingDto.getBookingTime());
//                availableTimeSlots.remove(slots);
//            }
//        }
//
////        if(bookedTimeSlot!=null) {
////            availableTimeSlots.remove(bookedTimeslot);
////            timeSlotManager.setAvailableTimeSlots(availableTimeSlots);
////        }
//
//
//        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//        executor.scheduleAtFixedRate(() -> {
//            System.out.println("Code executed!");
//            availableTimeSlots.add("10:15 AM");
//            availableTimeSlots.add("11:15 AM");
//            availableTimeSlots.add("12:15 PM");
//        },0,24, TimeUnit.HOURS);
//
//
//        booking.setDoctorId(bookingDto.getDoctorId());
//                booking.setPatientId(bookingDto.getPatientId());
//                if(booking.getBookingTime()!=null){
//                    bookingRepo.save(booking);
//                }else{
//                    System.out.println("This Slot Booked");
//                }
//
//    }
//
//}
//

//package com.procto.Service;
//
//import com.procto.Config.TimeSlotManager;
//import com.procto.Repository.BookingRepository;
//import com.procto.entity.Booking;
//import com.procto.payload.BookingDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//@Service
//public class BookingService {
//    @Autowired
//    private BookingRepository bookingRepo;
//
//    @Autowired
//    private TimeSlotManager timeSlotManager;
//
//    public void bookAnAppointment(BookingDto bookingDto) {
//        List<String> availableTimeSlots = timeSlotManager.getAvailableTimeSlots();
//
//        Booking booking = new Booking();
//        String requestedTimeSlot = bookingDto.getBookingTime();
//        boolean isSlotAvailable = availableTimeSlots.contains(requestedTimeSlot);
//
//        if (isSlotAvailable) {
//            for (String slots : availableTimeSlots) {
//                if (slots.equals(requestedTimeSlot)) {
//                    booking.setBookingTime(requestedTimeSlot);
//                    availableTimeSlots.remove(slots);
//                    break; // Break the loop after finding the matching slot
//                }
//            }
//
//            // Check if the specified doctorId and patientId combination is already booked
//            boolean isAlreadyBooked = bookingRepo.existsByDoctorIdAndPatientIdAndBookingTime(
//                    bookingDto.getDoctorId(), bookingDto.getPatientId(), requestedTimeSlot);
//
//            if (isAlreadyBooked) {
//                System.out.println("This slot is not available. Already booked for doctorId: "
//                        + bookingDto.getDoctorId() + " and patientId: " + bookingDto.getPatientId());
//                return;
//            }else{
//                System.out.println("This Slot Booked");
//            }
//
//            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//            executor.scheduleAtFixedRate(() -> {
//                System.out.println("Code executed!");
//                availableTimeSlots.add("10:15 AM");
//                availableTimeSlots.add("11:15 AM");
//                availableTimeSlots.add("12:15 PM");
//            }, 0, 24, TimeUnit.HOURS);
//
//            booking.setDoctorId(bookingDto.getDoctorId());
//            booking.setPatientId(bookingDto.getPatientId());
//            bookingRepo.save(booking);
//        } else {
//            System.out.println("This slot is not available.");
//        }
//    }
//}


package com.procto.Service;

import com.procto.Config.TimeSlotManager;
import com.procto.Repository.BookingRepository;
import com.procto.entity.Booking;
import com.procto.payload.BookingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private TimeSlotManager timeSlotManager;

    public ResponseEntity<String> bookAnAppointment(BookingDto bookingDto) {
        List<String> availableTimeSlots = timeSlotManager.getAvailableTimeSlots();

        Booking booking = new Booking();
        String requestedTimeSlot = bookingDto.getBookingTime();
        boolean isSlotAvailable = availableTimeSlots.contains(requestedTimeSlot);

        if (isSlotAvailable) {
            for (String slots : availableTimeSlots) {
                if (slots.equals(requestedTimeSlot)) {
                    booking.setBookingTime(requestedTimeSlot);
                    availableTimeSlots.remove(slots);
                    break; // Break the loop after finding the matching slot
                }
            }

            // Check if the specified doctorId and patientId combination is already booked
            boolean isAlreadyBooked = bookingRepo.existsByDoctorIdAndPatientIdAndBookingTime(
                    bookingDto.getDoctorId(), bookingDto.getPatientId(), requestedTimeSlot);

            if (isAlreadyBooked) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("This slot is not available. Already booked for doctorId: "
                                + bookingDto.getDoctorId() + " and patientId: " + bookingDto.getPatientId());
            } else {
                System.out.println("This Slot Booked");
            }

            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            executor.scheduleAtFixedRate(() -> {
                System.out.println("Code executed!");
                availableTimeSlots.add("10:15 AM");
                availableTimeSlots.add("11:15 AM");
                availableTimeSlots.add("12:15 PM");
            }, 0, 24, TimeUnit.HOURS);

            booking.setDoctorId(bookingDto.getDoctorId());
            booking.setPatientId(bookingDto.getPatientId());
            bookingRepo.save(booking);

            return ResponseEntity.ok("Booking successful");
        } else {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This slot is not available.");
        }
    }
}

