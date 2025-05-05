package org.lessons.spring.my_music_library.repositories;

import java.util.List;

import org.lessons.spring.my_music_library.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    List<Genre> findByNameContainingIgnoreCase(String name);

}