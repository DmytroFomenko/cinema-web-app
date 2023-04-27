package fdv.repository;


import fdv.entity.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {
    Seance findSeanceById(Long seanceId);
}

