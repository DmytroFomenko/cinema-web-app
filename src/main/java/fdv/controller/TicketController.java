package fdv.controller;

import fdv.entity.LoginData;
import fdv.entity.PlaceStatus;
import fdv.entity.Ticket;
import fdv.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@AllArgsConstructor
@SessionAttributes({"loggedUser"})
public class TicketController {

    SeanceService seanceService;
    FilmService filmService;
    TicketService ticketService;
    PlaceService placeService;
    PlaceKindService placeKindService;
    ServiceService serviceService;
    TicketServiceService ticketServiceService;



    @GetMapping("/buy_ticket_form")
    public String buyTicketForm(@ModelAttribute("seanceId") Long seanceId, Model model, HttpSession session) {
        model.addAttribute("seanceId", seanceId);
        model.addAttribute("tickets", ticketService.findTicketsBySeanceId(seanceId));
        model.addAttribute("film", filmService.findFilmById(seanceService.findSeanceById(seanceId).getFilmId()));

        LoginData loggedUser = (LoginData) session.getAttribute("loggedUser");

        model.addAttribute("loggedUser", loggedUser);

        model.addAttribute("placeServ", placeService);
        model.addAttribute("placeKindServ", placeKindService);
        model.addAttribute("ticketServiceServ", ticketServiceService);

        model.addAttribute("availableServices", serviceService.getAllAvailableServices());

        List<Ticket> reservedTickets = ticketService.getReservedTickets(loggedUser.getId(), seanceId);

        model.addAttribute("reservedTickets", reservedTickets);

        double seancePrice = seanceService.findSeanceById(seanceId).getBasePrice();
        model.addAttribute("seancePrice", seancePrice);

        double totalPrice = 0;

        for (Ticket tick : reservedTickets) {
            totalPrice += seancePrice;
            totalPrice += placeKindService.findPlaceKindById(placeService.findPlaceById(tick.getPlaceId()).getPlaceKindId()).getAddPrice();
            List<fdv.entity.TicketService> tickServices = ticketServiceService.findTicketServicesByTicketId(tick.getId());
            for (fdv.entity.TicketService tickService : tickServices) {
                totalPrice += serviceService.findServiceById(tickService.getServiceId()).getPrice();
            }
        }
        model.addAttribute("totalPrice", totalPrice);

        return "buy_ticket_form";
    }

    @PostMapping("/select_ticket")
    public String selectTicket(@ModelAttribute("ticketId") Long ticketId, RedirectAttributes redirectAttributes, Model model, HttpSession session) {
        //System.out.println(ticketId);
        Long seanceId = ticketService.findTicketById(ticketId).getSeanceId();
        redirectAttributes.addAttribute("seanceId", seanceId);

        LoginData client = (LoginData) session.getAttribute("loggedUser");
        ticketService.selectTicket(ticketId, client);

        return "redirect:/buy_ticket_form";
    }

    @PostMapping("/select_ticket_service")
    public String selectTicketService(@ModelAttribute("ticketId") Long ticketId, @ModelAttribute("serviceId") Long serviceId, RedirectAttributes redirectAttributes, Model model, HttpSession session) {

        Long seanceId = ticketService.findTicketById(ticketId).getSeanceId();
        redirectAttributes.addAttribute("seanceId", seanceId);

        ticketServiceService.selectTicketService(ticketId, serviceId);

        return "redirect:/buy_ticket_form";
    }

    @PostMapping("/buy_tickets")
    public String buyTickets(@ModelAttribute("totalPrice") double totalPrice, @ModelAttribute("seanceId") Long seanceId, Model model, HttpSession session) {
        LoginData loggedUser = (LoginData) session.getAttribute("loggedUser");

        List<Ticket> reservedTickets = ticketService.findTicketsBySeanceIdAndClientIdAndPlaceStatus(seanceId, loggedUser.getId(), PlaceStatus.RESERVED);

        for (Ticket tick : reservedTickets) {
            ticketService.buyTicket(tick);
        }

        return "redirect:/";
    }

}
