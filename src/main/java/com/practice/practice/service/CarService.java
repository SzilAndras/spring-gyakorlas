package com.practice.practice.service;

import com.practice.practice.dto.CarRequest;
import com.practice.practice.dto.CarResponse;
import com.practice.practice.repository.CarRepository;
import com.practice.practice.shared.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarResponse> getAll(){
        return MapperUtil.carsToCarsResponse(carRepository.findAll());
    }

    public CarResponse create(CarRequest car){
        return MapperUtil.carToCarResponse(this.carRepository.save(MapperUtil.carRequestToCar(car)));
    }

    public CarResponse update(CarResponse car) {
        return MapperUtil.carToCarResponse(this.carRepository.save(MapperUtil.carResponseToCar(car)));
    }

    public void delete(CarResponse car) {
        this.carRepository.delete(MapperUtil.carResponseToCar(car));
    }
}
