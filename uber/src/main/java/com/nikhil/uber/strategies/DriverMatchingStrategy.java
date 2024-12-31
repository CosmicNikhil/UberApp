package com.nikhil.uber.strategies;

import java.util.List;

import com.nikhil.uber.entities.Driver;
import com.nikhil.uber.entities.RideRequest;

public interface DriverMatchingStrategy {

    List<Driver> findMatchingDriver(RideRequest rideRequest);
}
