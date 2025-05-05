package org.lessons.spring.my_music_library.services;
import org.lessons.spring.my_music_library.models.Album;
import org.lessons.spring.my_music_library.models.Artist;
import org.lessons.spring.my_music_library.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    public List<Album> findByTitle(String title) {
        return albumRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Album> findAll(String query) {
        if (query != null && !query.isEmpty()) {
            return findByTitle(query);
        }
        return albumRepository.findAll();
    }

    public Optional<Album> findById(Integer id) {
        return albumRepository.findById(id);
    }

    public Boolean existsById(Integer id) {
        return albumRepository.existsById(id);
    }

    public Boolean exists(Album album) {
        return existsById(album.getId());
    }
}
