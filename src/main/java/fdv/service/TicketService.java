package fdv.service;

import fdv.entity.LoginData;
import fdv.entity.PlaceStatus;
import fdv.entity.Ticket;

import java.awt.*;
import java.util.List;

public interface TicketService {
    List<Ticket> getAllTickets();

    List<Ticket> findTicketsBySeanceId(Long seanceId);

    Ticket findTicketById(Long ticketId);

    boolean selectTicket(Long ticketId, LoginData client);

    List<Ticket> getReservedTickets(Long clientId, Long seanceId);
    List<Ticket> findTicketsBySeanceIdAndClientIdAndPlaceStatus(Long seanceId, Long clientId, PlaceStatus placeStatus);
    boolean buyTicket(Ticket ticket);
    List<Ticket> findTicketsByClientIdAndPlaceStatus(Long clientId, PlaceStatus placeStatus);

}
