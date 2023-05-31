package dfomenko.service.impl;


import dfomenko.entity.PlaceStatus;
import dfomenko.entity.Seance;
import dfomenko.entity.Ticket;
import dfomenko.entity.TicketAddition;
import dfomenko.repository.*;
import dfomenko.service.SeanceService;
import dfomenko.utils.TimeUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;


@Service
@AllArgsConstructor
public class SeanceServiceImpl implements SeanceService {
    private final SeanceRepository seanceRepository;
    private final TicketRepository ticketRepository;
    private final FilmRepository filmRepository;
    private final PlaceKindRepository placeKindRepository;
    private final PlaceRepository placeRepository;
    private final TicketAdditionRepository ticketAdditionRepository;
    private final AdditionRepository additionRepository;

    private final TimeUtils timeUtils;

    @Override
    public List<Seance> getAllSeances() {
        return seanceRepository.findAll();
    }

    @Override
    public Page<Seance> findAllByBeginTimeAfter(Timestamp fromTime, Pageable pageable) {
        return seanceRepository.findAllByBeginTimeAfter(fromTime, pageable);
    }

    @Override
    public Page<Seance> findAllByBeginTimeBetween(Timestamp fromTime, Timestamp toTime, Pageable pageable) {
        return seanceRepository.findAllByBeginTimeBetween(fromTime, toTime, pageable);
    }

    public Page<Seance> findAllByDateString(final String dateString, final Pageable pageable) throws ParseException {
        Timestamp fromTime = timeUtils.getTimestampFromDateString(dateString);
        Timestamp toTime = Timestamp.valueOf(fromTime.toLocalDateTime().plusDays(1));
        return this.findAllByBeginTimeBetween(fromTime, toTime, pageable);
    }

    @Override
    public Sort makeSort(String sortOrder) throws ParseException {
        if (sortOrder == null) {
            throw new NullPointerException("Source string \"sortOrder\" is null.");
        }
        if (sortOrder.equals("off")) {
            return Sort.unsorted();
        }
        String[] sortOrderParts = sortOrder.split("(?=[A-Z](?=[a-z]*(?>$)))");
        if (sortOrderParts.length != 2) {
            throw new ParseException("Source string must match \"sortOrderColumnNameAsc\" or \"sortOrderColumnNameDesc\", but present \"" + sortOrder + "\".", 0);
        }
        sortOrderParts[1] = sortOrderParts[1].toUpperCase();

        return Sort.by(Sort.Direction.valueOf(sortOrderParts[1]), sortOrderParts[0]);
    }

    @Override
    public Seance findSeanceById(Long seanceId) {
        return seanceRepository.findSeanceById(seanceId);
    }

    @Override
    public void createSeance(Seance seance) {
        seanceRepository.save(seance);
    }

    @Override
    public void updateSeance(Seance seance) {
        seanceRepository.save(seance);
    }

    @Override
    public boolean existsReservedOrBoughtTicket(Long seanceId) {
        return ticketRepository.existsTicketByPlaceStatusAndSeanceId(PlaceStatus.RESERVED, seanceId)
                || ticketRepository.existsTicketByPlaceStatusAndSeanceId(PlaceStatus.TAKEN, seanceId);
    }

    @Transactional
    @Override
    public void deleteSeanceById(Long seanceId) {
        seanceRepository.deleteSeanceById(seanceId);
    }

    @Override
    public boolean existsTimeCollision(Seance seanceForCheck) {
        List<Seance> allSeances = seanceRepository.findAll();
        Timestamp beginTimeForCheck = seanceForCheck.getBeginTime();
        Timestamp endTimeForCheck = seanceForCheck.calcEndTime(filmRepository.findFilmById(seanceForCheck.getFilmId()).getDuration());

        for (Seance seance : allSeances) {
            Timestamp existingBeginTime = seance.getBeginTime();
            Timestamp existingEndTime = seance.calcEndTime(filmRepository.findFilmById(seance.getFilmId()).getDuration());

            if ((existingBeginTime.after(beginTimeForCheck) && existingBeginTime.before(endTimeForCheck))
                    || (existingEndTime.after(beginTimeForCheck) && existingEndTime.before(endTimeForCheck))
                    || (existingBeginTime.before(beginTimeForCheck) && existingEndTime.after(endTimeForCheck))) {
                if (!seance.getId().equals(seanceForCheck.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public double calcTotalPriceOfSeanceTickets(List<Ticket> boughtTickets, Long seanceId) {
        double totalPrice = 0;
        double seancePrice = seanceRepository.findSeanceById(seanceId).getBasePrice();
        for (Ticket tick : boughtTickets) {
            totalPrice += seancePrice;
            totalPrice += placeKindRepository.findPlaceKindById(placeRepository.findPlaceById(tick.getPlaceId()).getPlaceKindId()).getAddPrice();
            List<TicketAddition> tickAdditions = ticketAdditionRepository.findTicketAdditionsByTicketId(tick.getId());
            for (TicketAddition tickAddition : tickAdditions) {
                totalPrice += additionRepository.findAdditionById(tickAddition.getAdditionId()).getPrice();
            }
        }
        return totalPrice;
    }

    @Override
    public double calcTotalPriceOfDaySeancesTickets(List<Seance> daySeances) {
        double totalPrice = 0;
        for (Seance s : daySeances) {
            List<Ticket> boughtSeanceTickets = ticketRepository.findTicketsBySeanceId(s.getId()).stream().filter((tick) -> tick.getPlaceStatus().equals(PlaceStatus.TAKEN)).toList();
            if (!boughtSeanceTickets.isEmpty()) {
                for (Ticket tick : boughtSeanceTickets) {
                    totalPrice += seanceRepository.findSeanceById(s.getId()).getBasePrice();
                    totalPrice += placeKindRepository.findPlaceKindById(placeRepository.findPlaceById(tick.getPlaceId()).getPlaceKindId()).getAddPrice();
                    List<TicketAddition> tickAdditions = ticketAdditionRepository.findTicketAdditionsByTicketId(tick.getId());
                    for (TicketAddition tickAddition : tickAdditions) {
                        totalPrice += additionRepository.findAdditionById(tickAddition.getAdditionId()).getPrice();
                    }
                }
            }
        }
        return totalPrice;
    }

    @Override
    public List<Seance> findDaySeancesByBeginTime(Timestamp beginTime) {
        Timestamp startOfDay = Timestamp.valueOf(beginTime.toLocalDateTime().toLocalDate().atStartOfDay());
        Timestamp endOfDay = Timestamp.valueOf(startOfDay.toLocalDateTime().plusDays(1));
        return seanceRepository.findAll().stream().filter((s) -> s.getBeginTime().after(startOfDay) && s.getBeginTime().before(endOfDay)).toList();
    }

    @Override
    public Page<Seance> findAll(Pageable pageable) {
        return seanceRepository.findAll(pageable);
    }

}
