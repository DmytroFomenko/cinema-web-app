package fdv.service.impl;


import fdv.entity.Seance;
import fdv.repository.SeanceRepository;
import fdv.service.SeanceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

// TODO: 15.04.2023  Добавил @Service и @AllArgsConstructor
@Service
@AllArgsConstructor
public class SeanceServiceImpl implements SeanceService {
    private SeanceRepository seanceRepository;

    @Override
    public List<Seance> getAllSeances() {
        return seanceRepository.findAll();
    }

    @Override
    public Seance findSeanceById(Long seanceId) {
        return seanceRepository.findSeanceById(seanceId);
    }

}
