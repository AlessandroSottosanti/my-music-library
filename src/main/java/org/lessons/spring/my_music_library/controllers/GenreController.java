package org.lessons.spring.my_music_library.controllers;

import java.util.List;

import org.lessons.spring.my_music_library.models.Genre;
import org.lessons.spring.my_music_library.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping()
    public String index(Model model) {
        List<Genre> genres = genreRepository.findAll();
        model.addAttribute(genres);
        // System.out.println(genres.toString());
        return "genres/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Genre newGenre = new Genre();
        model.addAttribute(newGenre);
        return "genres/create";
    }
    
    
}
