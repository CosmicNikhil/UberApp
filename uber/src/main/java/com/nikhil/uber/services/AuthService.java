package com.nikhil.uber.services;

import com.nikhil.uber.dto.DriverDto;
import com.nikhil.uber.dto.SignupDto;
import com.nikhil.uber.dto.UserDto;

public interface AuthService {

    String[] login(String email, String password);

    UserDto signup(SignupDto signupDto);

    DriverDto onboardNewDriver(Long userId, String vehicleId);

    String refreshToken(String refreshToken);
}
