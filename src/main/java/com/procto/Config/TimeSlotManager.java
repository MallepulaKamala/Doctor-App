package com.procto.Config;



import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TimeSlotManager {


    private List<String> availableTimeSlots;

    public TimeSlotManager(List<String> initialTimeSlots) {
        this.availableTimeSlots = initialTimeSlots;
    }

    public List<String> getAvailableTimeSlots() {
        return availableTimeSlots;
    }

    public void setAvailableTimeSlots(List<String> timeSlots) {
        this.availableTimeSlots = timeSlots;
    }

}



