package fdv.controller;


import fdv.entity.LoginData;
import fdv.entity.PlaceStatus;
import fdv.entity.Seance;
import fdv.entity.Ticket;
import fdv.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RestController
@AllArgsConstructor
@SessionAttributes({"loggedUser"})
//@RequestMapping("/api")
public class SeanceController {
    private SeanceService seanceService;
    private FilmService filmService;
    private RoleService roleService;
    private TicketService ticketService;
    private PlaceService placeService;



    @GetMapping("/")
    public String listSeances(Model model, HttpSession session) {
        model.addAttribute("seances", seanceService.getAllSeances());
        model.addAttribute("filmServ", filmService);

        LoginData loggedUser = (LoginData) session.getAttribute("loggedUser");
        model.addAttribute("loggedUser", loggedUser);

        List<Ticket> boughtTickets = null;

        if (loggedUser != null) {
            boughtTickets = ticketService.findTicketsByClientIdAndPlaceStatus(loggedUser.getId(), PlaceStatus.TAKEN);
            if (boughtTickets.isEmpty()) {
                boughtTickets = null;
            }
            model.addAttribute("boughtTickets", boughtTickets);
            model.addAttribute("seanceServ", seanceService);
            model.addAttribute("placeServ", placeService);

        }

        return "seances";
    }

    @GetMapping("/more_info")
    public String getMoreInfo(@ModelAttribute("seanceId") Long seanceId, Model model, HttpSession session) {

        Seance seance = seanceService.findSeanceById(seanceId);

        model.addAttribute("seance", seance);
        model.addAttribute("film", filmService.findFilmById(seance.getFilmId()));
        model.addAttribute("loggedUser", session.getAttribute("loggedUser"));
        model.addAttribute("clientRoleId", roleService.findRoleByRole("client").getId());

        return "more_info_seance";
    }

}
