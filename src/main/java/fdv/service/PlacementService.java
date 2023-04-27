package fdv.service;

import fdv.entity.Placement;


public interface PlacementService {
    Placement findPlacementById(Long placementId);
}
