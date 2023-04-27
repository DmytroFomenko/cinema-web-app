package fdv.service;


import fdv.entity.Seance;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SeanceService {

    List<Seance> getAllSeances();

    //Seance getSeanceById(Long id);

    Seance findSeanceById(Long seanceId);

}
