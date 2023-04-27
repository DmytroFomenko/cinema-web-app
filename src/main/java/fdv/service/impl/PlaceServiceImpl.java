package fdv.service.impl;

import fdv.entity.Place;
import fdv.repository.PlaceRepository;
import fdv.service.PlaceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    PlaceRepository placeRepository;

    @Override
    public List<Place> findPlacesByPlacementId(Long placementId) {
        return placeRepository.findPlacesByPlacementId(placementId);
    }

    @Override
    public Place findPlaceById(Long placeId) {
        return placeRepository.findPlaceById(placeId);
    }
}
