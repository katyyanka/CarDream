package org.bsuir.coursework.service;

import org.bsuir.coursework.model.Vehicle;
import org.bsuir.coursework.repository.UserRepository;
import org.bsuir.coursework.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository, UserRepository userRepository) {
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
    }

    public Vehicle findById(String id) {
        return vehicleRepository.getOne(id);
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle); }

    public void deleteById(String id) {
        Vehicle vehicle = findById(id);
        vehicleRepository.deleteById(id);
    }
}
