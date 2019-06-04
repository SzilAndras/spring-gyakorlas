package com.practice.practice.controller;

import com.practice.practice.dto.RentRequest;
import com.practice.practice.dto.RentResponse;
import com.practice.practice.service.RentService;
import com.practice.practice.shared.MapperUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rents")
public class RentController {

    private RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @PostMapping
    public RentResponse create(@RequestBody RentRequest rent){
        return this.rentService.create(rent);
    }

    @GetMapping
    public List<RentResponse> getAll(){
        return this.rentService.getAll();
    }
}
