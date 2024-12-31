package com.nikhil.uber.strategies.impl;

import org.springframework.stereotype.Service;

import com.nikhil.uber.entities.RideRequest;
import com.nikhil.uber.services.DistanceService;
import com.nikhil.uber.strategies.RideFareCalculationStrategy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {

    private final DistanceService distanceService;
    private static final double SURGE_FACTOR = 2;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(),
                rideRequest.getDropOffLocation());
        return distance*RIDE_FARE_MULTIPLIER*SURGE_FACTOR;
    }
}


/**
 * 
 *fare = distance × base fare multiplier × surge factor 
 * 
 **/
