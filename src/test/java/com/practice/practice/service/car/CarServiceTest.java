package com.practice.practice.service.car;

import com.practice.practice.dto.CarDto;
import com.practice.practice.dto.NewCarRequest;
import com.practice.practice.domain.Car;

import com.practice.practice.repository.CarRepository;
import com.practice.practice.repository.RentRepository;
import com.practice.practice.service.CarService;
import com.practice.practice.shared.CarMapperUtil;
import com.practice.practice.shared.CarType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;


public class CarServiceTest {

    private CarService carService;
    private CarRepository carRepository;
    private RentRepository rentRepository;

    @Captor
    ArgumentCaptor<Car> carArgumentCaptor;

    @BeforeEach
    void setup(){
        this.carRepository = mock(CarRepository.class);
        this.rentRepository = mock(RentRepository.class);
        this.carService = new CarService(this.carRepository, this.rentRepository);
    }

    @Test
    public void addCar(){
        NewCarRequest carRequest = NewCarRequest.builder()
                .dailyCost((long)6000)
                .plateNumber("asd-123")
                .type(CarType.Combi)
                .build();

        System.out.println("Add car log");
        System.out.println(carRequest.dailyCost);
        System.out.println(carRequest.plateNumber);
        System.out.println(carRequest.type);
        System.out.println(carRepository.save(CarMapperUtil.carRequestToCar(carRequest)).getIsRented());
        when(carRepository.save(any(Car.class))).thenReturn(CarMapperUtil.carRequestToCar(carRequest));

        CarDto carDto1 = this.carService.create(carRequest);

        verify(carRepository, times(1)).save(carArgumentCaptor.capture());

        assertEquals(carRequest.dailyCost, carArgumentCaptor.getValue().getDailyCost());
        assertEquals(carRequest.plateNumber, carArgumentCaptor.getValue().getPlateNumber());
        assertEquals(carRequest.type, carArgumentCaptor.getValue().getType());
        assertEquals(false, carArgumentCaptor.getValue().getIsRented());

        assertEquals(carRequest.dailyCost,carDto1.dailyCost);
        assertEquals(carRequest.plateNumber, carDto1.plateNumber);
        assertEquals(carRequest.type, carDto1.type);

    }
}
