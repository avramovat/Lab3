package mk.ukim.finki.wp.lab1.repository;

import mk.ukim.finki.wp.lab1.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab1.model.Event;
import mk.ukim.finki.wp.lab1.model.Location;
import mk.ukim.finki.wp.lab1.model.SavedBooking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryEventRepository {

    private List<SavedBooking> savedBookings = new ArrayList<>();

    public List<Event> findAll() {
        return DataHolder.eventList;
    }

    public List<Event> searchEvents(String text) {
        return DataHolder.eventList.stream()
                .filter(event -> event.getName().toLowerCase().contains(text.toLowerCase()) ||
                        event.getDescription().toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<SavedBooking> getSavedBookings() {
        return savedBookings;
    }

    public Optional<Event> save(String name, String description, double popularityScore, Location location) {
        Event newEvent = new Event(name, description, popularityScore, location);
        DataHolder.eventList.removeIf(e -> Objects.equals(e.getName(), name));
        DataHolder.eventList.add(newEvent);
        return Optional.of(newEvent);
    }

    public Optional<Event> findById(Long id) {
        return DataHolder.eventList.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public void deleteById(Long id) {
        DataHolder.eventList.removeIf(event -> Objects.equals(event.getId(), id));
    }

    public void addBooking(String evName, int numTickets) {
        boolean bookinExists = false;
        for (SavedBooking booking : savedBookings) {
            if (booking.getEventName().equals(evName)) {
                booking.setNumberOfTickets(booking.getNumberOfTickets() + numTickets);
                bookinExists = true;
                break;
            }
        }

        if (!bookinExists) {
            savedBookings.add(new SavedBooking(evName, numTickets));
        }
    }
}