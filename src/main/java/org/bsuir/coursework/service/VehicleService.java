package org.bsuir.coursework.service;

import org.bsuir.coursework.entity.Vehicle;
import org.bsuir.coursework.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle findById(String id) {
        return vehicleRepository.getOne(id);
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle saveVehicle(Vehicle vehicle) { return vehicleRepository.save(vehicle); }

    public void deleteById(String id) {
        vehicleRepository.deleteById(id);
    }
}
