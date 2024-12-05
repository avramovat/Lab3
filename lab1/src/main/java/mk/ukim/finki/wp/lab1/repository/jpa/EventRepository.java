package mk.ukim.finki.wp.lab1.repository.jpa;

import mk.ukim.finki.wp.lab1.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByLocation_id(Long location_id);
    @Query("SELECT e FROM Event e WHERE e.name LIKE %:keyword%")
    List<Event> searchEvents(@Param("keyword") String keyword);
}
