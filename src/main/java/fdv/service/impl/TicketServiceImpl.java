package fdv.service.impl;

import fdv.entity.LoginData;
import fdv.entity.PlaceStatus;
import fdv.entity.Ticket;
import fdv.repository.LoginDataRepository;
import fdv.repository.TicketRepository;
import fdv.repository.TicketServiceRepository;
import fdv.service.TicketService;
import fdv.service.TicketServiceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;
    private TicketServiceRepository ticketServiceRepository;
    private LoginDataRepository loginDataRepository;


    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> findTicketsBySeanceId(Long seanceId){
        return ticketRepository.findTicketsBySeanceId(seanceId);
    }

    @Override
    public Ticket findTicketById(Long ticketId){
        return ticketRepository.findTicketById(ticketId);
    }


    @Transactional
    @Override
    public boolean selectTicket(Long ticketId, LoginData client) {
        Ticket existingTicket = ticketRepository.findTicketById(ticketId);

        if (existingTicket.getPlaceStatus().equals(PlaceStatus.FREE)) {
            existingTicket.setPlaceStatus(PlaceStatus.RESERVED);
            existingTicket.setClientId(client.getId());
            ticketRepository.save(existingTicket);
            return true;
        } else if (existingTicket.getPlaceStatus().equals(PlaceStatus.RESERVED)
                    && existingTicket.getClientId().equals(client.getId())) {
            existingTicket.setPlaceStatus(PlaceStatus.FREE);
            existingTicket.setClientId(null);
            ticketRepository.save(existingTicket);
            ticketServiceRepository.deleteTicketServicesByTicketId(ticketId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Ticket> getReservedTickets(Long clientId, Long seanceId) {
        return ticketRepository.findAll().stream()
                .filter(tick -> Objects.equals(tick.getSeanceId(), seanceId))
                .filter(tick -> tick.getPlaceStatus() == PlaceStatus.RESERVED)
                .filter(tick -> Objects.equals(tick.getClientId(), clientId))
                .toList();
    }

    @Override
    public List<Ticket> findTicketsBySeanceIdAndClientIdAndPlaceStatus(Long seanceId, Long clientId, PlaceStatus placeStatus) {
        return ticketRepository.findTicketsBySeanceIdAndClientIdAndPlaceStatus(seanceId, clientId, placeStatus);
    }

    @Override
    public boolean buyTicket(Ticket ticket) {
        if (ticket.getPlaceStatus().equals(PlaceStatus.RESERVED)) {
            ticket.setPlaceStatus(PlaceStatus.TAKEN);

            String nickname = loginDataRepository.findLoginDataById(ticket.getClientId()).getNickname();
            long currentTimeMillis = System.currentTimeMillis();
            String ticketCode = nickname + currentTimeMillis;
            ticket.setCode(ticketCode);
            ticketRepository.save(ticket);
            return true;
        }
        return false;
    }

    @Override
    public List<Ticket> findTicketsByClientIdAndPlaceStatus(Long clientId, PlaceStatus placeStatus) {
        return ticketRepository.findTicketsByClientIdAndPlaceStatus(clientId, placeStatus);
    }
}
