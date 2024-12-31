package com.nikhil.uber.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.nikhil.uber.entities.Driver;
import com.nikhil.uber.entities.Ride;
import com.nikhil.uber.entities.RideRequest;
import com.nikhil.uber.entities.Rider;
import com.nikhil.uber.entities.enums.RideStatus;

public interface RideService {

    Ride getRideById(Long rideId);

    Ride createNewRide(RideRequest rideRequest, Driver driver);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);
}
