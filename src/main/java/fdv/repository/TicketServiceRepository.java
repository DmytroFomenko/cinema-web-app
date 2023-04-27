package fdv.repository;

import fdv.entity.TicketService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TicketServiceRepository extends JpaRepository<TicketService, Long> {
    boolean existsTicketServiceByTicketIdAndServiceId(Long ticketId, Long serviceId);

    TicketService findTicketServiceByTicketIdAndServiceId(Long ticketId, Long serviceId);

    long deleteTicketServicesByTicketId(Long ticketId);

    List<TicketService> findTicketServicesByTicketId(Long ticketId);


}
