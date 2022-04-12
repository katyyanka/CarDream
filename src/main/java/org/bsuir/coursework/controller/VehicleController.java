package org.bsuir.coursework.controller;

import org.bsuir.coursework.entity.Fuel;
import org.bsuir.coursework.entity.Vehicle;
import org.bsuir.coursework.service.FuelService;
import org.bsuir.coursework.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class VehicleController {
    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }


    @GetMapping("/vehicles")
    public String findAll(Model model){
        List<Vehicle> vehicles = vehicleService.findAll();
        model.addAttribute("vehicles", vehicles);
        return "vehicle/vehicle-list.html";
    }

    @GetMapping("/vehicle-create")
    public String createBusForm(Vehicle vehicle){
        return "vehicle/vehicle-create.html";
    }

    @PostMapping("/vehicle-create")
    public String createBus(Vehicle vehicle){
        vehicleService.saveVehicle(vehicle);
        return "redirect:/buses";
    }
}
