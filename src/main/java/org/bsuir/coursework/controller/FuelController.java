package org.bsuir.coursework.controller;

import org.bsuir.coursework.entity.Fuel;
import org.bsuir.coursework.entity.User;
import org.bsuir.coursework.service.FuelService;
import org.bsuir.coursework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FuelController {
    private final FuelService fuelService;

    @Autowired
    public FuelController(FuelService fuelService) {
        this.fuelService = fuelService;
    }


    @GetMapping("/fuels")
    public String findAll(Model model){
        List<Fuel> fuels = fuelService.findAll();
        model.addAttribute("fuels", fuels);
        return "fuel/fuel-list.html";
    }

    @GetMapping("/fuel-create")
    public String createFuelForm(Fuel fuel){
        return "fuel/fuel-create.html";
    }

    @PostMapping("/fuel-create")
    public String createFuel(Fuel fuel){
        fuelService.saveFuel(fuel);
        return "redirect:/fuels";
    }

    @GetMapping("fuel-delete/{id}")
    public String deleteFuel(@PathVariable("id") String id){
        fuelService.deleteById(id);
        return "redirect:/fuels";
    }

    @GetMapping("/fuel-update/{id}")
    public String updateFuelForm(@PathVariable("id") String id, Model model){
        Fuel fuel = fuelService.findById(id);
        model.addAttribute("fuel", fuel);
        return "fuel/fuel-update.html";
    }

    @PostMapping("/fuel-update")
    public String updateFuel(Fuel fuel){
        fuelService.saveFuel(fuel);
        return "redirect:/fuels";
    }

    @GetMapping("/fuel-price-increase")
    public String increasePrice(){
        fuelService.increasePrice();
        return "redirect:/fuels";
    }
}
