package org.lessons.spring.my_music_library.controllers;

import java.util.List;

import org.lessons.spring.my_music_library.models.Album;
import org.lessons.spring.my_music_library.models.Artist;
import org.lessons.spring.my_music_library.repositories.AlbumRepository;
import org.lessons.spring.my_music_library.repositories.ArtistRepository;
import org.lessons.spring.my_music_library.repositories.GenreRepository;
import org.lessons.spring.my_music_library.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
        
        model.addAttribute("album" , newAlbum);
        return "albums/create";
    }
    

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        albumRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found"));


        albumRepository.deleteById(id);
        return "redirect:/albums";
    }

}
