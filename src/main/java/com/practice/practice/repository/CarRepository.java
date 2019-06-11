package com.practice.practice.repository;

import com.practice.practice.domain.Car;
import com.practice.practice.shared.CarType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findAll();

    List<Car> findAllByIsRented(Boolean rented);

    @Query("SELECT c FROM Car c WHERE c.type = (:type)")
    List<Car> getCarsByType(@Param("type") CarType type);
}
