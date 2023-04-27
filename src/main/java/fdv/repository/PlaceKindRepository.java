package fdv.repository;

import fdv.entity.PlaceKind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlaceKindRepository extends JpaRepository<PlaceKind, Long> {
    PlaceKind findPlaceKindById(Long placeKindId);
}
