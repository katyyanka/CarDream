package org.bsuir.coursework.repository;

import org.bsuir.coursework.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, String> {
}
