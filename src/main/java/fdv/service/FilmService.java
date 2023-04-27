package fdv.service;


import fdv.entity.Film;

import java.util.List;

public interface FilmService {
    List<Film> getAllFilms();

    Film findFilmById(Long id);
}
