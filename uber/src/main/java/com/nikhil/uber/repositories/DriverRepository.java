package com.nikhil.uber.repositories;

import java.util.List;
import java.util.Optional;

import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nikhil.uber.entities.Driver;
import com.nikhil.uber.entities.User;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
	@Query(value = "SELECT d.*, ST_Distance(d.current_location, :pickupLocation) AS distance " +
			"FROM driver d " +
			"WHERE d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 10000) " +
			"ORDER BY distance " +
			"LIMIT 10", nativeQuery = true)
	List<Driver> findTenNearestDrivers(Point pickupLocation);

	@Query(value = "SELECT d.* " +
			"FROM driver d " +
			"WHERE d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 15000) " +
			"ORDER BY d.rating DESC " +
			"LIMIT 10", nativeQuery = true)
	List<Driver> findTenNearbyTopRatedDrivers(Point pickupLocation);
	
	Optional<Driver> findByUser(User user);
}




/**
 *  Native Query is sql 
 */

/**
 * ST_Distance(point1, point2) ST_DWithin(point1,point2,10000)
 * 
 */

/**
 * Query Explanation:
 * SELECT d.*, ST_Distance(d.current_location, :pickupLocation) AS distance:
 *   - Retrieves all columns of the `drivers` table (d.*).
 *   - Computes the distance between the driver's current location (`d.current_location`) 
 *     and the provided `:pickupLocation` using the ST_Distance function.
 *   - The computed distance is returned as an additional column named `distance`.
 * 
 * FROM drivers d:
 *   - Specifies the `drivers` table as the source of data and assigns it an alias `d`.
 * 
 * WHERE d.available = true:
 *   - Filters the results to include only drivers who are marked as available (boolean column).
 * 
 * AND ST_DWithin(d.current_location, :pickupLocation, 10000):
 *   - Filters drivers whose `current_location` is within a 10-kilometer radius of the `:pickupLocation`.
 *   - ST_DWithin is a PostGIS spatial function that checks if two geometries are within a specified distance (10,000 meters).
 * 
 * ORDER BY distance:
 *   - Sorts the filtered drivers by the computed distance in ascending order (closest drivers first).
 * 
 * LIMIT 10:
 *   - Limits the results to the top 10 drivers based on the sorted order.
 */
