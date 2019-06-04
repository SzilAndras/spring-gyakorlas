package com.practice.practice.dto;

import com.practice.practice.domain.Car;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Builder
public class RentResponse {
    @NotNull
    public Long id;

    @NotNull
    public Car car;

    @NotNull
    public Long totalPrice;

    @NotNull
    public OffsetDateTime startDate;

    @NotNull
    public OffsetDateTime expiry;
}
