package com.practice.practice.service;

import com.practice.practice.domain.Car;
import com.practice.practice.domain.Rent;
import com.practice.practice.dto.NewRentRequest;
import com.practice.practice.dto.RentDto;
import com.practice.practice.repository.RentRepository;
import com.practice.practice.shared.RentMapperUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class RentService {

    private RentRepository rentRepository;
    private CarService carService;

    public RentService(RentRepository rentRepository, CarService carService) {
        this.rentRepository = rentRepository;
        this.carService = carService;
    }

    public List<RentDto> getAll(){
        return RentMapperUtil.rentsToRentsDto(rentRepository.findAll());
    }

    public RentDto create(NewRentRequest newRent){
        Car car = this.carService.getById(newRent.carId);
        car.setIsRented(true);
        Rent rent = RentMapperUtil.rentRequestToRent(newRent);
        rent.setCar(car);
        return RentMapperUtil.rentToRentDto(this.rentRepository.save(rent));
    }

    public void delete(RentDto rentDto){
        this.rentRepository.delete(RentMapperUtil.rentDtoToRent(rentDto));
    }

    public RentDto getById(Long id){
        return RentMapperUtil.rentToRentDto(this.rentRepository.findByCar_Id(id).get());
    }

    public List<RentDto> getRecentRents(OffsetDateTime date){
        return RentMapperUtil.rentsToRentsDto(this.rentRepository.findAllByExpiryAfterAndStartDateBefore(date, date));
    }

    public  List<RentDto> getActualRents(OffsetDateTime date){
        OffsetDateTime before = OffsetDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), 0,0,0,0, date.getOffset());
        OffsetDateTime after = OffsetDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), 0,0,0,0, date.getOffset());
        after = after.plusDays(1);
        after = after.minusNanos(1);
        return RentMapperUtil.rentsToRentsDto(this.rentRepository.findAllByExpiryAfterAndExpiryBefore(before, after));
    }

    public List<RentDto> getRentPages(Integer page, Integer elements){
        return RentMapperUtil.rentsToRentsDto(this.rentRepository.findAllBy(PageRequest.of(page, elements)));
    }
}
