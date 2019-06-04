package com.practice.practice.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.practice.practice.shared.Ancestor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rent extends Ancestor {

    private Long totalPrice;

    @NotNull
    private OffsetDateTime startDate;

    @NotNull
    private OffsetDateTime expiry;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

}
