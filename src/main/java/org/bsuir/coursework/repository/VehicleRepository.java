package org.bsuir.coursework.repository;

import org.bsuir.coursework.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {
}