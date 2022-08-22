package com.example.filmproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FilmController {

    @Autowired
    FilmService filmService;

    @GetMapping(value="/film")
    public List<Film> findAll(){
        return filmService.findAll();
    }

    @GetMapping(value="/film/{id}")
    public Optional<Film> findById(@PathVariable Long id){
        return filmService.findById(id);
    }

    @PostMapping(value="/film")
    Film createOrSave(@RequestBody Film film)
    {return filmService.save(film);
    }

    @PutMapping(value="/film/{id}")
    Film update(@RequestBody Film newFilm, @PathVariable Long id) {
        return filmService.findById(id).map(film -> {
            film.setName(newFilm.getName());
            film.setGenre(newFilm.getGenre());
            return filmService.save(film);
        }).orElse(null);

    }

    @DeleteMapping(value="/film/{id}")
    void deleteById(@PathVariable Long id) {
        filmService.deleteById(id);
        System.out.println("delete");
    }
}
