package org.bsuir.coursework.controller;

import org.bsuir.coursework.model.Place;
import org.bsuir.coursework.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PlaceController {
    private final PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }


    @GetMapping("/places")
    public String findAll(Model model){
        return findPaginated(1, model);
    }

    @GetMapping("/place-create")
    public String createPlaceForm(Place place){
        return "place/place-create.html";
    }

    @PostMapping("/place-create")
    public String createPlace(Place place){
        try {
            placeService.savePlace(place);
            return "redirect:/places";
        } catch (Exception e) {
            return "redirect:/400";
        }
    }

    @GetMapping("place-delete/{id}")
    public String deletePlace(@PathVariable("id") String id){
        placeService.deleteById(id);
        return "redirect:/places";
    }

    @GetMapping("/place-update/{id}")
    public String updatePlaceForm(@PathVariable("id") String id, Model model){
        Place place = placeService.findById(id);
        model.addAttribute("place", place);
        return "place/place-update.html";
    }

    @PostMapping("/place-update")
    public String updatePlace(Place place){
        placeService.updatePlace(place);
        return "redirect:/places";
    }

    @GetMapping("/places/{pageNumber}")
    public String findPaginated(@PathVariable ( value = "pageNumber") int pageNumber, Model model){
        Page<Place> page = placeService.findPaginated(pageNumber, PlaceService.PLACES_PER_PAGE);
        List<Place> places = page.getContent();

        model.addAttribute("currentPage",pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("places", places);
        return "place/place-list";
    }
}
