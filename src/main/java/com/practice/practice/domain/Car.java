package com.practice.practice.domain;

import com.practice.practice.shared.Ancestor;
import com.practice.practice.shared.CarType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car extends Ancestor {

    @NotNull
    @Size(min = 7, max = 7)
    @Column(unique = true)
    private String plateNumber;

    @Enumerated(EnumType.STRING)
    private CarType type;

    @NotNull
    @Min(5000)
    @Max(25000)
    private Long dailyCost;

    @OneToOne(mappedBy = "car")
    private Rent rent;
}
