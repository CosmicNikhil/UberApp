package com.nikhil.uber.strategies.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nikhil.uber.entities.Driver;
import com.nikhil.uber.entities.RideRequest;
import com.nikhil.uber.repositories.DriverRepository;
import com.nikhil.uber.strategies.DriverMatchingStrategy;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;

    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
        return driverRepository.findTenNearestDrivers(rideRequest.getPickupLocation());
    }
}