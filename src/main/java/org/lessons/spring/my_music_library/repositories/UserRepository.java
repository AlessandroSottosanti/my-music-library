package org.lessons.spring.my_music_library.repositories;

import java.util.Optional;

import org.lessons.spring.my_music_library.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
