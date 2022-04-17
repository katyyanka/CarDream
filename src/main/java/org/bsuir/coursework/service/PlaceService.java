package org.bsuir.coursework.service;

import org.bsuir.coursework.entity.Place;
import org.bsuir.coursework.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (findById(place.getName())!=null) throw new Exception();
        return placeRepository.save(place);
    }
    public Place updatePlace(Place place) {
        return placeRepository.save(place);
    }

    public void deleteById(String id) {
        placeRepository.deleteById(id);
    }

    public  Page<Place> findPaginated(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);

        return this.placeRepository.findAll(pageable);
    }
}
