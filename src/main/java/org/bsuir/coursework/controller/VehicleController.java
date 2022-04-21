package org.bsuir.coursework.controller;

import org.bsuir.coursework.entity.Fuel;
import org.bsuir.coursework.entity.User;
import org.bsuir.coursework.entity.Vehicle;
import org.bsuir.coursework.service.FuelService;
import org.bsuir.coursework.service.UserService;
import org.bsuir.coursework.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class VehicleController {

    private final VehicleService vehicleService;

    private final FuelService fuelService;

    private final UserService userService;

    @Autowired
    public VehicleController(VehicleService vehicleService, FuelService fuelService, UserService userService) {
        this.vehicleService = vehicleService;
        this.fuelService = fuelService;
        this.userService = userService;
    }

    @GetMapping("/vehicles")
    public String findAll(Model model){
        List<Vehicle> vehicles = vehicleService.findAll();
        model.addAttribute("vehicles", vehicles);
        return "vehicle/vehicle-list.html";
    }

    @GetMapping("/vehicle-create")
    public String createBusForm(Model model, Vehicle vehicle){
        List<Fuel> fuels = fuelService.findAll();
        model.addAttribute("fuels", fuels);
        List<User> users = userService.getUserDriverWithoutVehicle();
        model.addAttribute("users", users);
        return "vehicle/vehicle-create.html";
    }

    @PostMapping("/vehicle-create")
    public String createBus(Vehicle vehicle){
        vehicleService.saveVehicle(vehicle);
        return "redirect:/vehicles";
    }
    @GetMapping("/vehicle-update/{id}")
    public String updateBusForm(@PathVariable("id") String id, Model model){
        Vehicle vehicle = vehicleService.findById(id);
        model.addAttribute("vehicle", vehicle);
        List<Fuel> fuels = fuelService.findAll();
        model.addAttribute("fuels", fuels);
        List<User> users = userService.getUserDriverWithoutVehicle();
        model.addAttribute("users", users);
        return "vehicle/vehicle-update.html";
    }

    @PostMapping("/vehicle-update/{id}")
    public String updateBus(Vehicle vehicle){
        vehicleService.saveVehicle(vehicle);
        return "redirect:/vehicles";
    }
    @GetMapping("vehicle-delete/{id}")
    public String deleteFuel(@PathVariable("id") String id){
        vehicleService.deleteById(id);
        return "redirect:/vehicles";
    }


}
