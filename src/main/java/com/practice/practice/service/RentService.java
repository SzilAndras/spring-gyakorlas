package com.practice.practice.service;

import com.practice.practice.dto.RentRequest;
import com.practice.practice.dto.RentResponse;
import com.practice.practice.repository.RentRepository;
import com.practice.practice.shared.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentService {

    private RentRepository rentRepository;

    public RentService(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    public List<RentResponse> getAll(){
        return MapperUtil.rentsToRentsRespone(rentRepository.findAll());
    }

    public RentResponse create(RentRequest rent){
        return MapperUtil.rentToRentResponse(rentRepository.save(MapperUtil.rentRequestToRent(rent)));
    }
}
