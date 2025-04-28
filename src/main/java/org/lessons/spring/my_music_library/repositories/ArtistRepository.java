package org.lessons.spring.my_music_library.repositories;

import org.lessons.spring.my_music_library.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
}