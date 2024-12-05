package mk.ukim.finki.wp.lab1.service;

import mk.ukim.finki.wp.lab1.model.EventBooking;

public interface EventBookingService {
    EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets);
}
