package org.lessons.spring.my_music_library.controllers;

import java.util.List;

import org.lessons.spring.my_music_library.models.Song;
import org.lessons.spring.my_music_library.repositories.AlbumRepository;
import org.lessons.spring.my_music_library.repositories.ArtistRepository;
import org.lessons.spring.my_music_library.repositories.GenreRepository;
import org.lessons.spring.my_music_library.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; // Added import
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


@Controller
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @GetMapping
    public String index(
            @RequestParam(name = "keyword", required = false) String keyword, // Changed parameter name from query to keyword
            Model model,
            HttpServletRequest request) {
        List<Song> songs;
        if (keyword != null && !keyword.isEmpty()) {
            // Use the new repository method to search by title
            songs = songRepository.findByTitleContainingIgnoreCase(keyword);
        } else {
            // If no keyword, get all songs
            songs = songRepository.findAll();
        }

        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("songs", songs);
        model.addAttribute("keyword", keyword); // Pass the keyword back to the view
        return "songs/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, HttpServletRequest request, Model model) {
        // Recupera la canzone dal repository
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found"));

        // Recupera l'album associato alla canzone

        // Aggiungi gli attributi al modello
        model.addAttribute("song", song);
        model.addAttribute("album", song.getAlbum()); // Aggiungi album al modello
        model.addAttribute("artist", song.getArtists());
        model.addAttribute("genres", song.getGenresAsString());
        model.addAttribute("currentUri", request.getRequestURI());

        return "songs/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Song song = new Song();
        model.addAttribute("song", song);
        model.addAttribute("formAction", "/songs/create");
        model.addAttribute("isEdit", false);
        model.addAttribute("albums", albumRepository.findAll());
        model.addAttribute("artists", artistRepository.findAll()); // assicurati che ci sia
        model.addAttribute("genres", genreRepository.findAll());
        return "songs/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("song") Song song, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("albums", albumRepository.findAll());
            model.addAttribute("artists", artistRepository.findAll());
            model.addAttribute("genres", genreRepository.findAll());
            model.addAttribute("formAction", "/songs/create");
            model.addAttribute("isEdit", false);
            return "songs/create";
        }

        songRepository.save(song);
        return "redirect:/songs/" + song.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found"));

        model.addAttribute("song", song);
        model.addAttribute("artists", artistRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("albums", albumRepository.findAll());
        model.addAttribute("album", song.getAlbum());
        model.addAttribute("isEdit", true);
        model.addAttribute("formAction", "/songs/edit/" + id);
        return "songs/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable("id") Integer id,
            @Valid @ModelAttribute("song") Song songForm,
            BindingResult bindingResult,
            Model model) {
    
        songRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found"));
    
        if (bindingResult.hasErrors()) {
            model.addAttribute("albums", albumRepository.findAll());
            model.addAttribute("artists", artistRepository.findAll());
            model.addAttribute("genres", genreRepository.findAll());
            model.addAttribute("formAction", "/songs/edit/" + id);
            model.addAttribute("isEdit", true);
            return "songs/edit";
        }
    
        songForm.setId(id);
        songRepository.save(songForm);
        return "redirect:/songs/" + id;
    }
    
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        songRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found"));

        songRepository.deleteById(id);
        return "redirect:/songs";
    }
}
