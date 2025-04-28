package org.lessons.spring.my_music_library.security;

import java.util.Optional;

import org.lessons.spring.my_music_library.models.User;
import org.lessons.spring.my_music_library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class DatabaseUserDetailService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userAttempt = userRepository.findByUsername(username);

        if (userAttempt.isEmpty()){
            throw new UnsupportedOperationException("There are no users available with username " + username);
        }

        return new DatabaseUserDetails(userAttempt.get());
    }

}
