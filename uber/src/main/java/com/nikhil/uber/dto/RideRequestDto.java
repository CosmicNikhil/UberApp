package com.nikhil.uber.dto;


import java.time.LocalDateTime;

import com.nikhil.uber.entities.enums.PaymentMethod;
import com.nikhil.uber.entities.enums.RideRequestStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestDto {

    private Long id;

    private PointDto pickupLocation;
    private PointDto dropOffLocation;
    private PaymentMethod paymentMethod;

    //Suppose a rider requests a ride on December 13, 2024, at 10:30 AM. 
    //This information can be stored in the requestedTime field as:
    //requestedTime = LocalDateTime.of(2024, 12, 13, 10, 30);
    private LocalDateTime requestedTime;

    private RiderDto rider;
    private Double fare;

    private RideRequestStatus rideRequestStatus;
}
