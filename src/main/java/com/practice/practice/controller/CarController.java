package com.practice.practice.controller;

import com.practice.practice.domain.Car;
import com.practice.practice.dto.NewCarRequest;
import com.practice.practice.dto.CarDto;
import com.practice.practice.service.CarService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<CarDto> getAll(){
        return carService.getAll();
    }

    @PostMapping
    public CarDto create(@RequestBody @Valid NewCarRequest car){
        return this.carService.create(car);
    }

    @PatchMapping
    public CarDto update(@RequestBody @Valid CarDto car) {
        return carService.update(car);
    }

    @DeleteMapping
    public void delete(@RequestBody @Valid CarDto car){
        this.carService.delete(car);
    }

    @GetMapping("rent")
    public List<CarDto> getRentedCars(){
        return this.carService.getCarsByRented(true);
    }

    @GetMapping("free")
    public List<CarDto> getFreeCars(){
        return this.carService.getCarsByRented(false);
    }

    @GetMapping("type/{type}")
    public List<CarDto> getCarsByType

}
