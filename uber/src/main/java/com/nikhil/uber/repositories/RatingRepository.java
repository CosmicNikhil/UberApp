package com.nikhil.uber.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nikhil.uber.entities.Driver;
import com.nikhil.uber.entities.Rating;
import com.nikhil.uber.entities.Ride;
import com.nikhil.uber.entities.Rider;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByRider(Rider rider);
    List<Rating> findByDriver(Driver driver);
    Optional<Rating> findByRide(Ride ride);
}
