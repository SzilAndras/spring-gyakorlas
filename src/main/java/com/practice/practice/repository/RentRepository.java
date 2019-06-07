package com.practice.practice.repository;

import com.practice.practice.domain.Rent;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.domain.Pageable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface RentRepository extends PagingAndSortingRepository<Rent, Long> {

    List<Rent> findAll();
    List<Rent> findAllByExpiryAfterAndStartDateBefore(OffsetDateTime dateTime, OffsetDateTime dateTime2);
    List<Rent> findAllByExpiryAfterAndExpiryBefore(OffsetDateTime before, OffsetDateTime after);
    List<Rent> findAllBy(Pageable pageable);

    void deleteByCar_Id(Long id);
    Optional<Rent> findByCar_Id(Long id);

}
