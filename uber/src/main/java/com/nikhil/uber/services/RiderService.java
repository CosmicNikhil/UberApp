package com.nikhil.uber.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.nikhil.uber.dto.DriverDto;
import com.nikhil.uber.dto.RideDto;
import com.nikhil.uber.dto.RideRequestDto;
import com.nikhil.uber.dto.RiderDto;
import com.nikhil.uber.entities.Rider;
import com.nikhil.uber.entities.User;

public interface RiderService {

    RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);

    DriverDto rateDriver(Long rideId, Integer rating);

    RiderDto getMyProfile();

    Page<RideDto> getAllMyRides(PageRequest pageRequest);

    Rider createNewRider(User user);

    Rider getCurrentRider();
}
