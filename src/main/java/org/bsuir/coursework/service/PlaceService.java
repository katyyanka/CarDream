package org.bsuir.coursework.service;

import org.bsuir.coursework.model.Place;
import org.bsuir.coursework.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.bsuir.coursework.helpers.parserStringToRadian.toRadian;

@Service
public class PlaceService {
    public static final int PLACES_PER_PAGE = 10;

    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public Place findById(String id) {
        return placeRepository.getOne(id);
    }

    public List<Place> findAll() {
        return placeRepository.findAll();
    }

    public Place savePlace(Place place) throws Exception {

        return placeRepository.save(place);
    }

    public Place updatePlace(Place place) {
        return placeRepository.save(place);
    }

    public void deleteById(String id) {
        placeRepository.deleteById(id);
    }

    public Page<Place> findPaginated(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        return this.placeRepository.findAll(pageable);
    }

    public static double distanceBetweenCities(Place first, Place second) {
        Double w1 = toRadian(first.getWidth()),
                l1 = toRadian(first.getLongitude()),
                w2 = toRadian(second.getWidth()),
                l2 = toRadian(second.getLongitude());
        Integer EARTH_RADIUS = 6373;

        double coefficient = 1.25; // учтём, что по прямой никто не ездит

        Double result = coefficient * EARTH_RADIUS *
                Math.atan(
                        Math.sqrt(
                                Math.pow(Math.cos(w2) * Math.sin(l2 - l1), 2) +
                                        Math.pow((Math.cos(w1) * Math.sin(w2) -
                                                Math.sin(w1) * Math.cos(w2) * Math.cos(l2 - l1)), 2))
                                / ((Math.sin(w1) * Math.sin(w2) + Math.cos(w1) * Math.cos(w2) * Math.cos(l2 - l1))));

        return Math.ceil(result);
    }

    static Double angularDifference(double w1, double l1, double w2, double l2) {
        return Math.acos(Math.sin(w1) * Math.sin(w2) + Math.cos(w1) * Math.cos(w2) * Math.cos(l2 - l1));
    }
}
