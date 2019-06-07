package com.practice.practice.dto;

import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Builder
public class NewRentRequest {

    @NotNull
    public Long carId;

    @NotNull
    public OffsetDateTime startDate;

    @NotNull
    public OffsetDateTime expiry;
}
