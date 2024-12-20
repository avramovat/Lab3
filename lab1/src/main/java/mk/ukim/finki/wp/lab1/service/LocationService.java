package mk.ukim.finki.wp.lab1.service;

import mk.ukim.finki.wp.lab1.model.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    List<Location> listAll();
    Optional<Location> findById(long id);
}
