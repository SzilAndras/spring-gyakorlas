package com.practice.practice.repository;

import com.practice.practice.domain.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findAll();
    List<Car> findAllByIsRented(Boolean rented);

}
