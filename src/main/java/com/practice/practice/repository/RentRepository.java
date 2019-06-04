package com.practice.practice.repository;

import com.practice.practice.domain.Rent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RentRepository extends CrudRepository<Rent, Long> {

    List<Rent> findAll();


}
