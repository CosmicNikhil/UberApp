package com.nikhil.uber.services;

import com.nikhil.uber.entities.Payment;
import com.nikhil.uber.entities.Ride;
import com.nikhil.uber.entities.enums.PaymentStatus;

public interface PaymentService {

    void processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus status);
}
