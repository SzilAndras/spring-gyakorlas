package com.practice.practice.domain;

import com.practice.practice.shared.Ancestor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rent extends Ancestor {

    @NotNull
    private OffsetDateTime startDate;

    @NotNull
    private OffsetDateTime expiry;

    @OneToOne(cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    @PreRemove
    void remove(){
        this.car.setIsRented(false);
    }
}
