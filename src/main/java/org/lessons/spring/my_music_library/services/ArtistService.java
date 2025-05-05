package org.lessons.spring.my_music_library.services;

import org.lessons.spring.my_music_library.models.Artist;
import org.lessons.spring.my_music_library.models.Genre;
import org.lessons.spring.my_music_library.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    // Metodo per ottenere tutti gli artisti con ricerca filtrata per nome
    public List<Artist> findAll(String query) {
        if (query != null && !query.isEmpty()) {
            return artistRepository.findByNameContainingIgnoreCase(query);
        }
        return artistRepository.findAll();
    }

    // Metodo per ottenere un singolo artista per ID
    public Optional<Artist> findById(Integer id) {
        return artistRepository.findById(id);
    }

    public Boolean existsById(Integer id) {
        return artistRepository.existsById(id);
    }

    public Boolean exists(Artist artist) {
        return existsById(artist.getId());
    }
}
