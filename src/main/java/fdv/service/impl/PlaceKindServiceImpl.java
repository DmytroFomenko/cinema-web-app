package fdv.service.impl;

import fdv.entity.PlaceKind;
import fdv.repository.PlaceKindRepository;
import fdv.repository.PlacementRepository;
import fdv.service.PlaceKindService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class PlaceKindServiceImpl implements PlaceKindService {

    PlaceKindRepository placeKindRepository;

    @Override
    public PlaceKind findPlaceKindById(Long placeKindId) {
        return placeKindRepository.findPlaceKindById(placeKindId);
    }
}
