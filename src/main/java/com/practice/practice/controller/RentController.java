package com.practice.practice.controller;

import com.practice.practice.dto.NewRentRequest;
import com.practice.practice.dto.RentDto;
import com.practice.practice.service.RentService;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/rents")
public class RentController {

    private RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @PostMapping
    public RentDto create(@RequestBody NewRentRequest rent){
        return this.rentService.create(rent);
    }

    @GetMapping
    public List<RentDto> getAll(){
        return this.rentService.getAll();
    }

    @DeleteMapping
    public void remove(@RequestBody RentDto rentDto){
        this.rentService.delete(rentDto);
    }

    @GetMapping("getById/{id}")
    public RentDto getById(@PathVariable Long id){
        return this.rentService.getById(id);
    }

    @GetMapping("current")
    public List<RentDto> getCurrent(){
        return this.rentService.getRecentRents(OffsetDateTime.now());
    }

    @PostMapping("actual")
    public List<RentDto> getActual(@RequestBody OffsetDateTime date){
        return this.rentService.getActualRents(date);
    }

    @GetMapping("page/{page}/{elements}")
    public List<RentDto> getPage(@PathVariable Integer page, @PathVariable Integer elements){
        return this.rentService.getRentPages(page, elements);
    }

}
