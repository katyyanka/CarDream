package org.bsuir.coursework.service;

import org.bsuir.coursework.entity.Fuel;
import org.bsuir.coursework.repository.FuelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelService {
    private final FuelRepository fuelRepository;

    @Autowired
    public FuelService(FuelRepository fuelRepository) {
        this.fuelRepository = fuelRepository;
    }

    public Fuel findById(String id) {
        return fuelRepository.getOne(id);
    }

    public List<Fuel> findAll() {
        return fuelRepository.findAll();
    }

    public Fuel saveFuel(Fuel user) {
        return fuelRepository.save(user);    }

    public void deleteById(String id) {
        fuelRepository.deleteById(id);
    }

    public int increasePrice(){
        return fuelRepository.increasePrice();
    }
}
