package org.lessons.spring.my_music_library.repositories;

import org.lessons.spring.my_music_library.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

}
