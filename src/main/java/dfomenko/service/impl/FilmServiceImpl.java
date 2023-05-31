package dfomenko.service.impl;


import dfomenko.entity.Film;
import dfomenko.repository.FilmRepository;
import dfomenko.service.FilmService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    @Override
    public List<Film> findAllFilms() {
        return filmRepository.findAll();
    }

    @Override
    public void createFilm(Film film) {
        filmRepository.save(film);
    }

    @Override
    public void updateFilm(Film film) {
        filmRepository.save(film);
    }

    @Override
    public Film findFilmById(Long filmId) {
        return filmRepository.findFilmById(filmId);
    }

    @Override
    public Film findFilmByName(String filmName) {
        return filmRepository.findFilmByName(filmName);
    }

    @Transactional
    @Override
    public void deleteFilmById(Long filmId) {
        filmRepository.deleteFilmById(filmId);
    }

}
