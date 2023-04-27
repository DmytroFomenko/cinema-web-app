package fdv.service.impl;

import fdv.entity.TicketService;
import fdv.repository.TicketServiceRepository;
import fdv.service.TicketServiceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
public class TicketServiceServiceImpl implements TicketServiceService {

    TicketServiceRepository ticketServiceRepository;

    @Override
    public boolean selectTicketService(Long ticketId, Long serviceId) {
        TicketService ticketService = ticketServiceRepository.findTicketServiceByTicketIdAndServiceId(ticketId, serviceId);

        if (ticketService == null) {
            TicketService newTicketService = new TicketService();
            newTicketService.setServiceId(serviceId);
            newTicketService.setTicketId(ticketId);
            ticketServiceRepository.save(newTicketService);
            return true;
        } else {
            ticketServiceRepository.delete(ticketService);
        }
        return false;
    }

    @Override
    public boolean existsTicketServiceByTicketIdAndServiceId(Long ticketId, Long serviceId) {
        return ticketServiceRepository.existsTicketServiceByTicketIdAndServiceId(ticketId, serviceId);
    }

    @Override
    public TicketService findTicketServiceByTicketIdAndServiceId(Long ticketId, Long serviceId) {
        return ticketServiceRepository.findTicketServiceByTicketIdAndServiceId(ticketId, serviceId);
    }


    @Transactional
    @Override
    public long deleteTicketServicesByTicketId(Long ticketId) {
        return ticketServiceRepository.deleteTicketServicesByTicketId(ticketId);
    }


    @Override
    public List<TicketService> findTicketServicesByTicketId(Long ticketId) {
        return ticketServiceRepository.findTicketServicesByTicketId(ticketId);
    }
}
