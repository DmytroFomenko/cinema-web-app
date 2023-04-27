package fdv.repository;

import fdv.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    Service findServiceById(Long serviceId);
}
