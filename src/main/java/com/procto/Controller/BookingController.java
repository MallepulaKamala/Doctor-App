package com.procto.Controller;

import com.procto.Service.BookingService;
import com.procto.payload.BookingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;
//    @PostMapping
//    public ResponseEntity<String> bookingAnAppointment(@RequestBody BookingDto bookingDto){
//        bookingService.bookAnAppointment(bookingDto);
//        return new ResponseEntity<>("your Appointment is booked", HttpStatus.OK);
//
//    }

    @PostMapping
    public ResponseEntity<String> bookingAnAppointment(@RequestBody BookingDto bookingDto) {
        ResponseEntity<String> response = bookingService.bookAnAppointment(bookingDto);

        if (response.getStatusCode().is2xxSuccessful()) {
            return new ResponseEntity<>("Your appointment is booked", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response.getBody(), response.getStatusCode());
        }
    }
}
