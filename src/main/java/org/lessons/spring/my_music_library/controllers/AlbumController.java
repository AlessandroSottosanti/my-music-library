package org.lessons.spring.my_music_library.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.lessons.spring.my_music_library.models.Album;
import org.lessons.spring.my_music_library.models.Artist;
import org.lessons.spring.my_music_library.models.Song;
import org.lessons.spring.my_music_library.repositories.AlbumRepository;
import org.lessons.spring.my_music_library.repositories.ArtistRepository;
import org.lessons.spring.my_music_library.repositories.GenreRepository;
import org.lessons.spring.my_music_library.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @GetMapping()
    public String index(Model model) {
        List<Album> albums = albumRepository.findAll();
        List<Artist> artists = artistRepository.findAll();
        model.addAttribute("albums", albums);
        model.addAttribute("artists", artists);
        return "albums/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found"));

        model.addAttribute("album", album);
        return "albums/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Album newAlbum = new Album();
        // Initialize with a few empty songs for the form
        List<Song> initialSongs = new ArrayList<>();
        for (int i = 0; i < 3; i++) { // Start with 3 empty song slots
            initialSongs.add(new Song());
        }
        newAlbum.setSongs(initialSongs);

        model.addAttribute("album", newAlbum);
        model.addAttribute("artists", artistRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        return "albums/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("album") Album albumForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("album", albumForm);
            model.addAttribute("artists", artistRepository.findAll());
            model.addAttribute("genres", genreRepository.findAll());
            return "albums/create";
        }

        // Process submitted songs: set the album reference and filter out empty ones
        List<Song> submittedSongs = albumForm.getSongs();
        if (submittedSongs != null) {
            List<Song> validSongs = submittedSongs.stream()
                .filter(song -> song.getTitle() != null && !song.getTitle().isBlank() && song.getDuration() != null) // Basic check for valid song data
                .peek(song -> song.setAlbum(albumForm)) // Set the album reference
                .collect(Collectors.toList());
            albumForm.setSongs(validSongs);
        } else {
            albumForm.setSongs(new ArrayList<>());
        }


        albumRepository.save(albumForm);

        return "redirect:/albums/" + albumForm.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Album not found"));

        model.addAttribute("album", album);
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("artists", artistRepository.findAll());
        return "albums/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable("id") Integer id,
            @Valid @ModelAttribute("album") Album albumForm,
            BindingResult bindingResult,
            Model model) {

        albumRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Album not found"));

        if (bindingResult.hasErrors()) {

            model.addAttribute("album", albumForm);
            model.addAttribute("artists", artistRepository.findAll());
            model.addAttribute("genres", genreRepository.findAll());
            return "album/edit";
        }

        albumForm.setId(id);
        albumRepository.save(albumForm);

        return "redirect:/albums/" + id;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        albumRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found"));

        albumRepository.deleteById(id);
        return "redirect:/albums";
    }

}
