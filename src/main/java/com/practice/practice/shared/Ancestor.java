package com.practice.practice.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ancestor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, name = "id")
    private Long id;

    protected OffsetDateTime lastModification;
    @Column(updatable = false)
    protected OffsetDateTime createdTime;

    @PrePersist
    void create(){
        this.createdTime = OffsetDateTime.now();
    }

    @PreUpdate
    void update(){
        this.lastModification = OffsetDateTime.now();
    }
}
