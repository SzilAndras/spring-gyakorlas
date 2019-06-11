package com.practice.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
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
