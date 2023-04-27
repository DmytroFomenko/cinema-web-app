package fdv.service.impl;

import fdv.entity.Placement;
import fdv.repository.PlacementRepository;
import fdv.service.PlacementService;
import fdv.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class PlacementServiceImpl implements PlacementService {

    PlacementRepository placementRepository;

    public Placement findPlacementById(Long placementId) {
        return placementRepository.findPlacementById(placementId);
    }
}
