package mk.ukim.finki.wp.lab1.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab1.model.Event;
import mk.ukim.finki.wp.lab1.model.EventBooking;
import mk.ukim.finki.wp.lab1.service.EventBookingService;
import mk.ukim.finki.wp.lab1.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/events")
public class EventBookingController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventBookingService eventBookingService;



    @PostMapping("/event-booking")
    public String bookEvent(@RequestParam("rad") String eventName,
                            @RequestParam("numTickets") int numTickets,
                            @RequestParam("attendeeName") String attendeeName,
                            @RequestParam("attendeeAddress") String attendeeAddress, Model model) {


        Optional<Event> selectedEvent = eventService.findEventByName(eventName);
        if (selectedEvent.isPresent()) {
            Event event = selectedEvent.get();


            EventBooking booking = new EventBooking(
                    event.getName(),  // Event name
                    attendeeName,     // Attendee name
                    attendeeAddress,  // Attendee address
                    (long) numTickets // Number of tickets
            );



            model.addAttribute("eventName", selectedEvent.get().getName());
            model.addAttribute("attendeeName", attendeeName);
            model.addAttribute("attendeeAddress", attendeeAddress);
            model.addAttribute("numberOfTickets", numTickets);

            return "bookingConfirmation";
        } else {
            model.addAttribute("error", "Event not found!");
            return "listEvents";
        }
    }
}