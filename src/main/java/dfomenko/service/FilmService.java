package dfomenko.service;


import dfomenko.entity.Film;

import java.util.List;

public interface FilmService {
    List<Film> findAllFilms();
    void createFilm(Film film);
    void updateFilm(Film film);
    Film findFilmById(Long id);
    Film findFilmByName(String filmName);
    void deleteFilmById(Long filmId);

}
