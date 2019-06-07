package com.practice.practice.dto;

import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Builder
public class RentDto {
    @NotNull
    public Long id;

    @NotNull
    public CarDto car;

    @NotNull
    public OffsetDateTime startDate;

    @NotNull
    public OffsetDateTime expiry;
}
