package com.nikhil.uber.services.impl;

import static com.nikhil.uber.entities.enums.Role.DRIVER;

import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nikhil.uber.dto.DriverDto;
import com.nikhil.uber.dto.SignupDto;
import com.nikhil.uber.dto.UserDto;
import com.nikhil.uber.entities.Driver;
import com.nikhil.uber.entities.User;
import com.nikhil.uber.entities.enums.Role;
import com.nikhil.uber.exceptions.ResourceNotFoundException;
import com.nikhil.uber.exceptions.RuntimeConflictException;
import com.nikhil.uber.repositories.UserRepository;
import com.nikhil.uber.security.JWTService;
import com.nikhil.uber.services.AuthService;
import com.nikhil.uber.services.DriverService;
import com.nikhil.uber.services.RiderService;
import com.nikhil.uber.services.WalletService;

import lombok.RequiredArgsConstructor;




@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final RiderService riderService;
	private final WalletService walletService;
	private final DriverService driverService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JWTService jwtService;

	@Override
	public String[] login(String email, String password) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(email, password)
				);

		User user = (User) authentication.getPrincipal();

		String accessToken = jwtService.generateAccessToken(user);
		String refreshToken = jwtService.generateRefreshToken(user);

		return new String[]{accessToken, refreshToken};
	}

	@Override
	@Transactional
	public UserDto signup(SignupDto signupDto) {
		User user = userRepository.findByEmail(signupDto.getEmail()).orElse(null);
		if(user != null)
			throw new RuntimeConflictException("Cannot signup, User already exists with email "+signupDto.getEmail());

		User mappedUser = modelMapper.map(signupDto, User.class);
		mappedUser.setRoles(Set.of(Role.RIDER));
		mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
		User savedUser = userRepository.save(mappedUser);

		//        create user related entities
		riderService.createNewRider(savedUser);
		walletService.createNewWallet(savedUser);

		return modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public DriverDto onboardNewDriver(Long userId, String vehicleId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id "+userId));

		if(user.getRoles().contains(DRIVER))
			throw new RuntimeConflictException("User with id "+userId+" is already a Driver");

		Driver createDriver = Driver.builder()
				.user(user)
				.rating(0.0)
				.vehicleId(vehicleId)
				.available(true)
				.build();
		user.getRoles().add(DRIVER);
		userRepository.save(user);
		Driver savedDriver = driverService.createNewDriver(createDriver);
		return modelMapper.map(savedDriver, DriverDto.class);
	}

	@Override
	public String refreshToken(String refreshToken) {
		Long userId = jwtService.getUserIdFromToken(refreshToken);
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found " +
				"with id: "+userId));

		return jwtService.generateAccessToken(user);
	}
}











