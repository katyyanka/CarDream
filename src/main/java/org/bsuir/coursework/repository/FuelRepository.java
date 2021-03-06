package org.bsuir.coursework.repository;

import org.bsuir.coursework.model.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface FuelRepository extends JpaRepository<Fuel, String> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE fuel SET price = price + 0.01",
            nativeQuery = true)
    int increasePrice();
}
