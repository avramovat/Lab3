package mk.ukim.finki.wp.lab1.bootstrap;

import jakarta.annotation.PostConstruct;
import lombok.extern.apachecommons.CommonsLog;
import mk.ukim.finki.wp.lab1.model.Event;
import mk.ukim.finki.wp.lab1.model.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> eventList = new ArrayList<>();
    public static List<Location> locationList = new ArrayList<>();

    @PostConstruct
    public void init(){
        locationList.add(new Location("City Hall", "123 Main St", "500", "Spacious event hall in the city center"));
        locationList.add(new Location("University Auditorium", "45 University Ave", "300", "Large auditorium with modern facilities"));
        locationList.add(new Location("Open Air Park", "78 Park Ln", "1000", "Beautiful open-air venue in the park"));
        locationList.add(new Location("Conference Center", "90 Business Rd", "200", "Professional conference room"));
        locationList.add(new Location("Community Center", "55 Maple St", "150", "Cozy community space for smaller events"));

        eventList.add(new Event("Concert", "Music concert in the park", 8.5, locationList.get(0)));
        eventList.add(new Event("Workshop", "Java programming workshop", 7.2, locationList.get(1)));
        eventList.add(new Event("Webinar", "Online seminar on web programming", 6.8, locationList.get(2)));
        eventList.add(new Event("Art Exhibition", "Exhibition of modern art", 9.1, locationList.get(3)));
        eventList.add(new Event("Book Fair", "Annual fair for book lovers", 8.0, locationList.get(4)));
        eventList.add(new Event("Food Festival", "Festival with different food vendors", 7.9, locationList.get(0)));
        eventList.add(new Event("Tech Conference", "Conference on latest tech trends", 9.5, locationList.get(1)));
        eventList.add(new Event("Movie Screening", "Outdoor movie screening event", 6.3, locationList.get(2)));
        eventList.add(new Event("Dance Show", "Dance performances from local artists", 7.4, locationList.get(3)));
        eventList.add(new Event("Charity Run", "Fundraising run for charity", 8.7, locationList.get(4)));
    }
}
