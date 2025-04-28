package org.lessons.spring.my_music_library.controllers;

import org.lessons.spring.my_music_library.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

import org.lessons.spring.my_music_library.models.Album;
import org.lessons.spring.my_music_library.models.Artist;
import org.lessons.spring.my_music_library.repositories.AlbumRepository;
import org.lessons.spring.my_music_library.repositories.ArtistRepository;
import org.lessons.spring.my_music_library.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
@RequestMapping("/artists")
public class ArtistController {
    
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
        List<Artist> artists = artistRepository.findAll();
        model.addAttribute("artists", artists);
        return "artists/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, HttpServletRequest request, Model model ) {
        Artist artist = artistRepository.findById(id).get();
        model.addAttribute("artist", artist);
        return "artists/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Artist newArtist = new Artist();

        model.addAttribute("newArtist" ,newArtist);
        return "artists/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("artist") Artist artist, BindingResult bindingResult , Model model) {
        if(bindingResult.hasErrors()){
            return "artists/create";
        }
        artistRepository.save(artist);
        model.addAttribute("message", "Artist created successfully!");
        return "redirect:/artist/" + artist.getId();
    }
    

    @GetMapping("/edit/{id}")
    public String edit( @PathVariable("id") Integer id, Model model){
        Artist artist = artistRepository.findById(id).get();

        model.addAttribute("artist" , artist);
        model.addAttribute("albums", artist.getAlbums()); 
        return "artists/edit";
    }
    

    // TODO: Delete

    
    
    
}
