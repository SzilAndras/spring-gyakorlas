package com.practice.practice.dto;

import com.practice.practice.shared.CarType;
import lombok.Builder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
public class CarRequest {

    @NotNull
    @Size(min = 7, max = 7)
    public String plateNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    public CarType type;

    @NotNull
    @Min(5000)
    @Max(25000)
    public Long dailyCost;
}
