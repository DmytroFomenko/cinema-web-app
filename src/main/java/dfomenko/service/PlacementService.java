package dfomenko.service;

import dfomenko.entity.Placement;

import java.util.List;


public interface PlacementService {
    Placement findPlacementById(Long placementId);

    List<Placement> findAllPlacements();

    void createPlacement(Placement placement);

    void updatePlacement(Placement placement);

    void deletePlacementById(Long placementId);
}
