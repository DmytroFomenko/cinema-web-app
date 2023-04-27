package fdv.service.impl;


import fdv.entity.Film;
import fdv.repository.FilmRepository;
import fdv.service.FilmService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FilmServiceImpl implements FilmService {
    private FilmRepository filmRepository;

    @Override
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @Override
    public Film findFilmById(Long filmId) {return filmRepository.findFilmById(filmId);}

}
