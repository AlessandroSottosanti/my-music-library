package org.lessons.spring.my_music_library.services;

import org.lessons.spring.my_music_library.models.Genre;
import org.lessons.spring.my_music_library.models.Song;
import org.lessons.spring.my_music_library.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    // Metodo per ottenere tutti i generi con ricerca filtrata per nome
    public List<Genre> findAll(String query) {
        if (query != null && !query.isEmpty()) {
            return genreRepository.findByNameContainingIgnoreCase(query);
        }
        return genreRepository.findAll();
    }

    // Metodo per ottenere un singolo genere per ID
    public Optional<Genre> findById(Integer id) {
        return genreRepository.findById(id);
    }

    public Boolean existsById(Integer id) {
        return genreRepository.existsById(id);
    }

    public Boolean exists(Genre genre) {
        return existsById(genre.getId());
    }
}
