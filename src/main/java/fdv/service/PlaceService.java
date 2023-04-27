package fdv.service;

import fdv.entity.Place;
import java.util.List;


public interface PlaceService {
    List<Place> findPlacesByPlacementId(Long placementId);
    Place findPlaceById(Long placeId);
}
