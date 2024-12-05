package mk.ukim.finki.wp.lab1.service.impl;

import mk.ukim.finki.wp.lab1.model.EventBooking;
import mk.ukim.finki.wp.lab1.service.EventBookingService;
import org.springframework.stereotype.Service;

@Service
public class EventBookingServiceImpl implements EventBookingService {
    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        return new EventBooking(eventName, attendeeName, attendeeAddress, (long) numberOfTickets);
    }
}