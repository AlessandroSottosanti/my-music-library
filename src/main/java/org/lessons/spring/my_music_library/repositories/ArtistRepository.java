package org.lessons.spring.my_music_library.repositories;

import java.util.List; // Moved import for List here
import org.lessons.spring.my_music_library.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    List<Artist> findByNameContainingIgnoreCase(String nameKeyword);
}