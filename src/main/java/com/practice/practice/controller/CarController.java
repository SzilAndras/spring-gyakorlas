package com.practice.practice.controller;

import com.practice.practice.dto.CarRequest;
import com.practice.practice.dto.CarResponse;
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
    public List<CarResponse> getAll(){
        return carService.getAll();
    }

    @PostMapping
    public CarResponse create(@RequestBody @Valid CarRequest car){
        return this.carService.create(car);
    }

    @PatchMapping
    public CarResponse update(@RequestBody @Valid CarResponse car) {
        return carService.update(car);
    }

    @DeleteMapping
    public void delete(@RequestBody @Valid CarResponse car){
        this.carService.delete(car);
    }

}
