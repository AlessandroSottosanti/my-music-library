package org.lessons.spring.my_music_library.repositories;

import java.util.List;

import org.lessons.spring.my_music_library.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Integer> {
    // List<Song> findByTitleContainingIgnoreCaseOrGenreContainingIgnoreCaseOrArtistContainingIgnoreCaseOrAlbumContainingIgnoreCase(String title, String genre, String artist, String album);
}
