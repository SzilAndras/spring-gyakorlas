package com.practice.practice.shared;

import com.practice.practice.domain.Car;
import com.practice.practice.dto.NewCarRequest;
import com.practice.practice.dto.CarDto;

import java.util.List;
import java.util.stream.Collectors;

public class CarMapperUtil {

    public static CarDto carToCarDto(Car car){
        return CarDto.builder()
                .dailyCost(car.getDailyCost())
                .id(car.getId())
                .plateNumber(car.getPlateNumber())
                .type(car.getType())
                .isRented(car.getIsRented())
                .build();
    }

    public static List<CarDto> carsToCarsDto(List<Car> cars){
        return cars.stream().map(car ->
                CarDto.builder()
                        .plateNumber(car.getPlateNumber())
                        .id(car.getId())
                        .dailyCost(car.getDailyCost())
                        .type(car.getType())
                        .isRented(car.getIsRented())
                        .build()
        ).collect(Collectors.toList());
    }

    public static Car carRequestToCar(NewCarRequest newCarRequest){
        Car newCar = new Car();
        newCar.setDailyCost(newCarRequest.dailyCost);
        newCar.setPlateNumber(newCarRequest.plateNumber);
        newCar.setType(newCarRequest.type);
        newCar.setIsRented(false);
        return newCar;
    }

    public static Car carDtoToCar(CarDto carRequest){
        Car newCar = new Car();
        newCar.setDailyCost(carRequest.dailyCost);
        newCar.setPlateNumber(carRequest.plateNumber);
        newCar.setType(carRequest.type);
        newCar.setId(carRequest.id);
        newCar.setIsRented(carRequest.isRented);
        return newCar;
    }
}
