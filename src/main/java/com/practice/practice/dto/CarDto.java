package com.practice.practice.dto;

import com.practice.practice.shared.CarType;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
public class CarDto {

    @NotNull
    public Long id;

    @Size(max = 7, min = 7)
    public String plateNumber;

    @NotNull
    public CarType type;

    @NotNull
    public Long dailyCost;

    public Boolean isRented;
}
