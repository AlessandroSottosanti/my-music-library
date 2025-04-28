package org.lessons.spring.my_music_library.controllers;

import java.util.List;

import org.lessons.spring.my_music_library.models.Genre;
import org.lessons.spring.my_music_library.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;





@Controller
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping()
    public String index(Model model) {
        List<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);
        return "genres/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Genre newGenre = new Genre();
        model.addAttribute("newGenre", newGenre);
        return "genres/create";
    }
    
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("genre") Genre newGenre, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "genres/create";
        }
        
        genreRepository.save(newGenre);
        return "redirect:/genres";
    }
    

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id ,Model model) {
        Genre genreToModify = genreRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found"));
        model.addAttribute("genre", genreToModify);
        return "genres/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("genre") Genre modifiedGenre, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "genres/create";
        }
        
        genreRepository.save(modifiedGenre);        
        return "redirect:/genres";
    }
    
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        genreRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found"));
            
        genreRepository.deleteById(id);
        return "redirect:/genres";
    }
    
    
}
