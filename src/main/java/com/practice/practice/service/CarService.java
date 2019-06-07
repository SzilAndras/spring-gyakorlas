package com.practice.practice.service;

import com.practice.practice.domain.Car;
import com.practice.practice.domain.Rent;
import com.practice.practice.dto.NewCarRequest;
import com.practice.practice.dto.CarDto;
import com.practice.practice.repository.CarRepository;
import com.practice.practice.repository.RentRepository;
import com.practice.practice.shared.CarMapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private CarRepository carRepository;
    private RentRepository rentRepository;

    public CarService(CarRepository carRepository, RentRepository rentRepository) {
        this.carRepository = carRepository;
        this.rentRepository = rentRepository;
    }

    public List<CarDto> getAll(){
        return CarMapperUtil.carsToCarsDto(carRepository.findAll());
    }

    public CarDto create(NewCarRequest car){
        return CarMapperUtil.carToCarDto(this.carRepository.save(CarMapperUtil.carRequestToCar(car)));
    }

    public CarDto update(CarDto car) {
        return CarMapperUtil.carToCarDto(this.carRepository.save(CarMapperUtil.carDtoToCar(car)));
    }

    public void delete(CarDto car) {
        Optional<Rent> temp =  this.rentRepository.findByCar_Id(car.id);
        if (temp.isPresent()){
            this.rentRepository.delete(temp.get());
        }
        this.carRepository.delete(CarMapperUtil.carDtoToCar(car));
    }

    public Car getById(Long id){
        Optional<Car> car = this.carRepository.findById(id);
        if (car.isPresent()){
            return car.get();
        } else {
            return null;
        }
    }

    public List<CarDto> getCarsByRented(boolean rented){
        return CarMapperUtil.carsToCarsDto(this.carRepository.findAllByIsRented(rented));
    }



}
