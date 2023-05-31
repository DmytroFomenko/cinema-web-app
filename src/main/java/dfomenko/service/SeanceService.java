package dfomenko.service;


import dfomenko.entity.Seance;
import dfomenko.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

public interface SeanceService {

    List<Seance> getAllSeances();

    Page<Seance> findAllByBeginTimeAfter(Timestamp fromTime, Pageable pageable);

    Page<Seance> findAllByBeginTimeBetween(Timestamp fromTime, Timestamp toTime, Pageable pageable);

    Page<Seance> findAllByDateString(String dateString, Pageable pageable) throws ParseException;

    Page<Seance> findAll(Pageable pageable);

    Sort makeSort(String sortOrder) throws ParseException;

    Seance findSeanceById(Long seanceId);

    List<Seance> findDaySeancesByBeginTime(Timestamp beginTime);

    void createSeance(Seance seance);

    void updateSeance(Seance seance);

    boolean existsReservedOrBoughtTicket(Long seanceId);

    boolean existsTimeCollision(Seance seance);

    double calcTotalPriceOfSeanceTickets(List<Ticket> boughtTickets, Long seanceId);

    double calcTotalPriceOfDaySeancesTickets(List<Seance> daySeances);

    void deleteSeanceById(Long seanceId);

}
