package com.practice.practice.shared;
import com.practice.practice.domain.Car;
import com.practice.practice.domain.Rent;
import com.practice.practice.dto.NewRentRequest;
import com.practice.practice.dto.RentDto;

import java.util.List;
import java.util.stream.Collectors;

public class RentMapperUtil {


    public static List<RentDto> rentsToRentsDto(List<Rent> rents){
        return rents.stream().map(rent ->
                RentDto.builder()
                        .id(rent.getId())
                        .car(CarMapperUtil.carToCarDto(rent.getCar()))
                        .expiry(rent.getExpiry())
                        .startDate(rent.getStartDate())
                        .build()).collect(Collectors.toList());
    }

    public static Rent rentRequestToRent(NewRentRequest rent){
        Rent newRent = new Rent();
        newRent.setExpiry(rent.expiry);
        newRent.setStartDate(rent.startDate);
        return newRent;
    }

    public static RentDto rentToRentDto(Rent rent){
        return RentDto.builder()
                .startDate(rent.getStartDate())
                .expiry(rent.getExpiry())
                .car(CarMapperUtil.carToCarDto(rent.getCar()))
                .id(rent.getId())
                .build();
    }

    public static Rent rentDtoToRent(RentDto rentDto){
        Rent rent = new Rent();
        Car car = CarMapperUtil.carDtoToCar(rentDto.car);
        rent.setCar(car);
        rent.setStartDate(rentDto.startDate);
        rent.setExpiry(rentDto.expiry);
        rent.setId(rentDto.id);
        return rent;
    }

}
