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
import org.mockito.Mockito;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    void setup() {
        this.carRepository = mock(CarRepository.class);
        this.rentRepository = mock(RentRepository.class);
        this.carService = new CarService(this.carRepository, this.rentRepository);
        carArgumentCaptor = ArgumentCaptor.forClass(Car.class);
    }

    @Test
    public void addCar() {
        NewCarRequest carRequest = NewCarRequest.builder()
                .dailyCost((long) 6000)
                .plateNumber("asd-123")
                .type(CarType.Combi)
                .build();

        CarDto carDto = CarDto.builder()
                .dailyCost(carRequest.dailyCost)
                .isRented(false)
                .plateNumber(carRequest.plateNumber)
                .type(carRequest.type)
                .id(1L)
                .build();

        Mockito.when(this.carRepository.save(any(Car.class))).thenReturn(CarMapperUtil.carDtoToCar(carDto));

        CarDto carDto1 = this.carService.create(carRequest);

        Mockito.verify(carRepository, times(1)).save(carArgumentCaptor.capture());

        assertEquals(carRequest.dailyCost, carArgumentCaptor.getValue().getDailyCost());
        assertEquals(carRequest.plateNumber, carArgumentCaptor.getValue().getPlateNumber());
        assertEquals(carRequest.type, carArgumentCaptor.getValue().getType());
        assertEquals(false, carArgumentCaptor.getValue().getIsRented());

        assertEquals(carRequest.dailyCost, carDto1.dailyCost);
        assertEquals(carRequest.plateNumber, carDto1.plateNumber);
        assertEquals(carRequest.type, carDto1.type);
        assertEquals((long) 1, (long) carDto1.id);
        assertEquals(false, carDto1.isRented);
    }

    @Test
    public void getAllCar() {
        List<Car> cars = new ArrayList<>();
        CarDto carDto1 = CarDto.builder()
                .dailyCost(7000L)
                .isRented(false)
                .plateNumber("HFB-635")
                .type(CarType.Coupe)
                .id(1L)
                .build();
        CarDto carDto2 = CarDto.builder()
                .dailyCost(5500L)
                .isRented(false)
                .plateNumber("SDF-532")
                .type(CarType.Combi)
                .id(2L)
                .build();
        cars.add(CarMapperUtil.carDtoToCar(carDto1));
        cars.add(CarMapperUtil.carDtoToCar(carDto2));

        Mockito.when(this.carRepository.findAll()).thenReturn(cars);

        List<CarDto> cars1 = this.carService.getAll();

        Mockito.verify(carRepository, times(1)).findAll();

        assertEquals(cars1.size(), cars.size());

        for (int i = 0; i < cars1.size(); i++) {
            assertEquals(cars.get(i).getDailyCost(), cars1.get(i).dailyCost);
            assertEquals(cars.get(i).getIsRented(), cars1.get(i).isRented);
            assertEquals(cars.get(i).getPlateNumber(), cars1.get(i).plateNumber);
            assertEquals(cars.get(i).getType(), cars1.get(i).type);
            assertEquals(cars.get(i).getId(), cars1.get(i).id);
        }
    }

    @Test
    public void delete() {
        CarDto carDto1 = CarDto.builder()
                .dailyCost(7000L)
                .isRented(false)
                .plateNumber("HFB-635")
                .type(CarType.Coupe)
                .id(1L)
                .build();

        this.carService.delete(carDto1);

        Mockito.verify(carRepository, times(1)).delete(carArgumentCaptor.capture());

        assertEquals(carDto1.dailyCost, carArgumentCaptor.getValue().getDailyCost());
        assertEquals(carDto1.plateNumber, carArgumentCaptor.getValue().getPlateNumber());
        assertEquals(carDto1.type, carArgumentCaptor.getValue().getType());
        assertEquals(carDto1.isRented, carArgumentCaptor.getValue().getIsRented());
        assertEquals(carDto1.id, carArgumentCaptor.getValue().getId());
    }

    @Test
    public void update() {
        CarDto carDto1 = CarDto.builder()
                .dailyCost(7000L)
                .isRented(false)
                .plateNumber("HFB-635")
                .type(CarType.Coupe)
                .id(1L)
                .build();

        Mockito.when(this.carRepository.save(any(Car.class))).thenReturn(CarMapperUtil.carDtoToCar(carDto1));

        this.carService.update(carDto1);

        Mockito.verify(carRepository, times(1)).save(carArgumentCaptor.capture());

        assertEquals(carDto1.dailyCost, carArgumentCaptor.getValue().getDailyCost());
        assertEquals(carDto1.plateNumber, carArgumentCaptor.getValue().getPlateNumber());
        assertEquals(carDto1.type, carArgumentCaptor.getValue().getType());
        assertEquals(carDto1.isRented, carArgumentCaptor.getValue().getIsRented());
        assertEquals(carDto1.id, carArgumentCaptor.getValue().getId());
    }

    @Test
    public void getById() {
        CarDto carDto = CarDto.builder()
                .dailyCost(7000L)
                .isRented(false)
                .plateNumber("HFB-635")
                .type(CarType.Coupe)
                .id(1L)
                .build();
        Optional<Car> carOptional = Optional.of(CarMapperUtil.carDtoToCar(carDto));
        Mockito.when(this.carRepository.findById(any(Long.class))).thenReturn(carOptional);

        CarDto carDto1 = this.carService.getById(carDto.id);

        Mockito.verify(carRepository, times(1)).findById(carDto.id);

        assertEquals(carDto1.dailyCost, carOptional.get().getDailyCost());
        assertEquals(carDto1.plateNumber, carOptional.get().getPlateNumber());
        assertEquals(carDto1.type, carOptional.get().getType());
        assertEquals(carDto1.isRented, carOptional.get().getIsRented());
        assertEquals(carDto1.id, carOptional.get().getId());
    }

    @Test
    public void getCarsByRented(){
        List<Car> rentedCars = new ArrayList<>();
        List<Car> cars = new ArrayList<>();
        CarDto carDto1 = CarDto.builder()
                .dailyCost(7000L)
                .isRented(true)
                .plateNumber("HFB-635")
                .type(CarType.Coupe)
                .id(1L)
                .build();
        CarDto carDto2 = CarDto.builder()
                .dailyCost(5500L)
                .isRented(true)
                .plateNumber("SDF-532")
                .type(CarType.Combi)
                .id(2L)
                .build();
        CarDto carDto3 = CarDto.builder()
                .dailyCost(8000L)
                .isRented(false)
                .plateNumber("dsa-532")
                .type(CarType.Combi)
                .id(3L)
                .build();
        rentedCars.add(CarMapperUtil.carDtoToCar(carDto1));
        rentedCars.add(CarMapperUtil.carDtoToCar(carDto2));
        cars.add(CarMapperUtil.carDtoToCar(carDto3));


        Mockito.when(this.carRepository.findAllByIsRented(true)).thenReturn(rentedCars);
        Mockito.when(this.carRepository.findAllByIsRented(false)).thenReturn(cars);


        List<CarDto> cars1Rented = this.carService.getCarsByRented(true);
        List<CarDto> cars1NotRented = this.carService.getCarsByRented(false);


        Mockito.verify(carRepository, times(1)).findAllByIsRented(true);
        Mockito.verify(carRepository, times(1)).findAllByIsRented(false);


        assertEquals(cars1Rented.size(), rentedCars.size());
        assertEquals(cars1NotRented.size(), cars.size());


        for (int i = 0; i < cars1Rented.size(); i++) {
            assertEquals(rentedCars.get(i).getDailyCost(), cars1Rented.get(i).dailyCost);
            assertEquals(rentedCars.get(i).getIsRented(), cars1Rented.get(i).isRented);
            assertEquals(rentedCars.get(i).getPlateNumber(), cars1Rented.get(i).plateNumber);
            assertEquals(rentedCars.get(i).getType(), cars1Rented.get(i).type);
            assertEquals(rentedCars.get(i).getId(), cars1Rented.get(i).id);
        }

        for (int i = 0; i < cars1NotRented.size(); i++) {
            assertEquals(cars.get(i).getDailyCost(), cars1NotRented.get(i).dailyCost);
            assertEquals(cars.get(i).getIsRented(), cars1NotRented.get(i).isRented);
            assertEquals(cars.get(i).getPlateNumber(), cars1NotRented.get(i).plateNumber);
            assertEquals(cars.get(i).getType(), cars1NotRented.get(i).type);
            assertEquals(cars.get(i).getId(), cars1NotRented.get(i).id);
        }
    }
}
