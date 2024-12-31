package com.nikhil.uber.services;

import com.nikhil.uber.dto.DriverDto;
import com.nikhil.uber.dto.RiderDto;
import com.nikhil.uber.entities.Ride;

public interface RatingService {

    DriverDto rateDriver(Ride ride, Integer rating);
    RiderDto rateRider(Ride ride, Integer rating);

    void createNewRating(Ride ride);
}
