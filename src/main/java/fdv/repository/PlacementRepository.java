package fdv.repository;

import fdv.entity.Placement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlacementRepository extends JpaRepository<Placement, Long> {
    Placement findPlacementById(Long placementId);
}
