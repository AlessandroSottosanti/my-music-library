package org.lessons.spring.my_music_library.services;

import java.util.List;
import java.util.Optional;

import org.lessons.spring.my_music_library.models.Song;
import org.lessons.spring.my_music_library.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongService {

    @Autowired
    SongRepository songRepository;

    public List<Song> findAll(String query) {
        if (query != null && !query.isEmpty()) {
            return songRepository.findByTitleContainingIgnoreCase(query);
        }
        return songRepository.findAll();
    }

    public Optional<Song> getById(Integer id){
        Optional<Song> songAttempt = songRepository.findById(id);

        return songAttempt;
    }


    public Boolean existsById(Integer id){
        return songRepository.existsById(id);
    }

    public Boolean exists(Song song) {
        return existsById(song.getId());
    }
}
