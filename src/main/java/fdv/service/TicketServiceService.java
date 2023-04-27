package fdv.service;


import fdv.entity.TicketService;

import java.util.List;

public interface TicketServiceService {
    boolean selectTicketService(Long ticketId, Long serviceId);
    boolean existsTicketServiceByTicketIdAndServiceId(Long ticketId, Long serviceId);

    TicketService findTicketServiceByTicketIdAndServiceId(Long ticketId, Long serviceId);

    long deleteTicketServicesByTicketId(Long ticketId);

    List<TicketService> findTicketServicesByTicketId(Long ticketId);

}
