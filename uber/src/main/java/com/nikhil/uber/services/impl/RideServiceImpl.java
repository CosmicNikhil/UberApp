package com.nikhil.uber.services.impl;

import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nikhil.uber.entities.Driver;
import com.nikhil.uber.entities.Ride;
import com.nikhil.uber.entities.RideRequest;
import com.nikhil.uber.entities.Rider;
import com.nikhil.uber.entities.enums.RideRequestStatus;
import com.nikhil.uber.entities.enums.RideStatus;
import com.nikhil.uber.exceptions.ResourceNotFoundException;
import com.nikhil.uber.repositories.RideRepository;
import com.nikhil.uber.services.RideRequestService;
import com.nikhil.uber.services.RideService;

import lombok.RequiredArgsConstructor;


//Common Attributes
//- id, pickupLocation, dropOffLocation, rider, paymentMethod, fare

//Unique to RideRequest
//- requestedTime: Timestamp for the request
//- rideRequestStatus: Status of the ride request

//Unique to Ride
//- driver: Assigned driver
//- createdTime: Timestamp for ride creation
//- rideStatus: Status of the ride
//- otp: Verification code
//- startedAt, endedAt: Ride start and end times

//Summary
//- RideRequest: Represents the request phase (status and timing of the request)
//- Ride: Represents the execution phase (driver, verification, and ride timeline)




@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {

    private final RideRepository rideRepository;
    private final RideRequestService rideRequestService;
    private final ModelMapper modelMapper;

    @Override
    public Ride getRideById(Long rideId) {
        return rideRepository.findById(rideId)
                .orElseThrow(() -> new ResourceNotFoundException("Ride not found with id: "+rideId));
    }


    @Override
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {
        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);

        Ride ride = modelMapper.map(rideRequest, Ride.class);
        ride.setRideStatus(RideStatus.CONFIRMED);
        ride.setDriver(driver);
        ride.setOtp(generateRandomOTP());
        ride.setId(null);

        rideRequestService.update(rideRequest);
        return rideRepository.save(ride);
    }

    @Override
    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
        ride.setRideStatus(rideStatus);
        return rideRepository.save(ride);
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest) {
        return rideRepository.findByRider(rider, pageRequest);
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest) {
        return rideRepository.findByDriver(driver, pageRequest);
    }

    private String generateRandomOTP() {
        Random random = new Random();
        int otpInt = random.nextInt(10000);  //0 to 9999
        return String.format("%04d", otpInt);
    }
}