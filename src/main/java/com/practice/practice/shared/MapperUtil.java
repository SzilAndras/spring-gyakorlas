package com.practice.practice.shared;

import com.practice.practice.domain.Car;
import com.practice.practice.domain.Rent;
import com.practice.practice.dto.CarRequest;
import com.practice.practice.dto.CarResponse;
import com.practice.practice.dto.RentRequest;
import com.practice.practice.dto.RentResponse;

import java.util.List;
import java.util.stream.Collectors;

public class MapperUtil {

    public static CarResponse carToCarResponse(Car car){
        return CarResponse.builder()
                .dailyCost(car.getDailyCost())
                .id(car.getId())
                .plateNumber(car.getPlateNumber())
                .type(car.getType())
                .build();
    }

    public static List<CarResponse> carsToCarsResponse(List<Car> cars){
        return cars.stream().map(car ->
                CarResponse.builder()
                .plateNumber(car.getPlateNumber())
                .id(car.getId())
                .dailyCost(car.getDailyCost())
                .type(car.getType())
                .build()
                ).collect(Collectors.toList());
    }

    public static Car carRequestToCar(CarRequest carRequest){
        Car newCar = new Car();
        newCar.setDailyCost(carRequest.dailyCost);
        newCar.setPlateNumber(carRequest.plateNumber);
        newCar.setType(carRequest.type);
        return newCar;
    }

    public static Car carResponseToCar(CarResponse carRequest){
        Car newCar = new Car();
        newCar.setDailyCost(carRequest.dailyCost);
        newCar.setPlateNumber(carRequest.plateNumber);
        newCar.setType(carRequest.type);
        newCar.setId(carRequest.id);
        return newCar;
    }

    public static List<RentResponse> rentsToRentsRespone(List<Rent> rents){
        return rents.stream().map(rent ->
                RentResponse.builder()
                        .id(rent.getId())
                        .car(rent.getCar())
                        .expiry(rent.getExpiry())
                        .startDate(rent.getStartDate())
                        .totalPrice(rent.getTotalPrice())
                        .build()).collect(Collectors.toList());
    }

    public static Rent rentRequestToRent(RentRequest rent){
        Rent newRent = new Rent();
        newRent.setExpiry(rent.expiry);
        newRent.setStartDate(rent.startDate);
        newRent.setCar(rent.car);
        return newRent;
    }

    public static RentResponse rentToRentResponse(Rent rent){
        return RentResponse.builder()
                .totalPrice(rent.getTotalPrice())
                .startDate(rent.getStartDate())
                .expiry(rent.getExpiry())
                .car(rent.getCar())
                .id(rent.getId())
                .build();
    }

}
