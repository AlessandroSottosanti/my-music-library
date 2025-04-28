package org.lessons.spring.my_music_library.controllers;

import java.util.List;

import org.lessons.spring.my_music_library.models.Album;
import org.lessons.spring.my_music_library.models.Artist;
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
// Import RedirectAttributes if you plan to add flash messages for delete/update success
// import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            @RequestParam(name = "query", required = false) String query,
            Model model,
            HttpServletRequest request) {
        List<Song> songs;
        if (query != null && !query.isEmpty()) {
            songs = songRepository.findAll();
            // .findByTitleContainingIgnoreCaseOrGenreContainingIgnoreCaseOrArtistContainingIgnoreCaseOrAlbumContainingIgnoreCase(
            // query, query, query, query);
        } else {
            songs = songRepository.findAll();
        }

        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("songs", songs);
        return "songs/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, HttpServletRequest request, Model model) {
        Song song = songRepository.findById(id).get();
        model.addAttribute("song", song);
        model.addAttribute("artist", song.getArtist());
        model.addAttribute("album", song.getAlbum());
        model.addAttribute("genres", song.getGenresAsString());
        model.addAttribute("currentUri", request.getRequestURI());
        return "songs/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Song song = new Song();
        List<Album> albums = albumRepository.findAll();
        model.addAttribute("albums", albums);
        model.addAttribute("song", song);
        model.addAttribute("type", "create");
        model.addAttribute("formAction", "/songs/create");

        return "songs/create-update";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("song") Song song, BindingResult bindingResult, Model model) { // Removed
                                                                                                              // comma
        if (bindingResult.hasErrors()) {
            return "songs/create-update";
        }
        songRepository.save(song);

        model.addAttribute("message", "Song created successfully!");

        return "redirect:/songs/" + song.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found"));
                
        model.addAttribute("song", song);
        model.addAttribute("artists", artistRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("album", song.getAlbum());
        model.addAttribute("type", "update");
        model.addAttribute("formAction", "/songs/edit/" + id);
        return "songs/create-update";
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

            model.addAttribute("artists", artistRepository.findAll());
            model.addAttribute("genres", genreRepository.findAll());
            return "songs/create-update";
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
