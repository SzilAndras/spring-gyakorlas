package com.practice.practice.dto;

import com.practice.practice.domain.Car;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Builder
public class RentRequest {

    public Car car;

    @NotNull
    public OffsetDateTime startDate;

    @NotNull
    public OffsetDateTime expiry;
}
