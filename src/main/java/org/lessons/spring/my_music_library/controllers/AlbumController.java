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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private SmartValidator validator;

    @GetMapping()
    public String index(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Artist> artists = artistRepository.findAll();

        if (keyword != null && !keyword.isBlank()) {
            String lowerKeyword = keyword.toLowerCase();

            artists = artists.stream()
                    .map(artist -> {
                        List<Album> filteredAlbums = artist.getAlbums().stream()
                                .filter(album -> album.getTitle() != null &&
                                        album.getTitle().toLowerCase().contains(lowerKeyword))
                                .collect(Collectors.toList());
                        artist.setAlbums(filteredAlbums);
                        return artist;
                    })
                    .filter(artist -> !artist.getAlbums().isEmpty())
                    .collect(Collectors.toList());
        }

        model.addAttribute("artists", artists);
        model.addAttribute("keyword", keyword);
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
        Integer removeSongIndex = null;

        initialSongs.add(new Song());

        newAlbum.setSongs(initialSongs);

        model.addAttribute("album", newAlbum);
        model.addAttribute("artists", artistRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("NumberOfSongs", 1);
        model.addAttribute("removeSongIndex", removeSongIndex);
        return "albums/create";
    }

    @PostMapping("/create")
    public String store(@ModelAttribute("album") Album albumForm,
            BindingResult bindingResult,
            @RequestParam(value = "action", required = false) String action,
            @RequestParam(value = "removeSongIndex", required = false) Integer removeSongIndex,
            Model model) {

        if (action != null && action.startsWith("removeSong")) {
            int index = Integer.parseInt(action.substring("removeSong".length()));
            if (index >= 0 && index < albumForm.getSongs().size()) {
                albumForm.getSongs().remove(index);
            }

            model.addAttribute("album", albumForm);
            model.addAttribute("artists", artistRepository.findAll());
            model.addAttribute("genres", genreRepository.findAll());
            System.out.println("STO RIMUOVENDO UNA CANZONE");

            return "albums/create";
        }

        if ("addSong".equals(action)) {
            albumForm.getSongs().add(new Song()); // Aggiungi una nuova riga vuota
            System.out.println("STO AGGIUNGENDO UNA CANZONE");

            model.addAttribute("album", albumForm);
            model.addAttribute("artists", artistRepository.findAll());
            model.addAttribute("genres", genreRepository.findAll());
            return "albums/create";
        }

        // Assegna gli artisti alle canzoni
        if (albumForm.getArtists() != null && !albumForm.getArtists().isEmpty()) {
            for (Song song : albumForm.getSongs()) {
                song.setArtists(albumForm.getArtists());
                song.setAlbum(albumForm);
            }
            System.out.println("ASSEGNAZIONE DEGLI ARTISTI ALLE CANZONI");
        }

        validator.validate(albumForm, bindingResult);

        // Verifica eventuali errori di validazione
        if (bindingResult.hasErrors()) {

            model.addAttribute("album", albumForm);
            model.addAttribute("artists", artistRepository.findAll());
            model.addAttribute("genres", genreRepository.findAll());

            System.out.println("ERRORE NELLA COMPILAZIONE DEL FORM");
            System.out.println(bindingResult);
            System.out.println("ERRORE NELLA COMPILAZIONE DEL FORM");

            return "albums/create";
        }

        System.out.println("STO SALVANDO");
        // Salva l'album e le canzoni con gli artisti associati
        albumRepository.save(albumForm);

        return "redirect:/albums/" + albumForm.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Album not found"));

        // Se getSongs() è null, inizializza la lista
        if (album.getSongs() == null) {
            album.setSongs(new ArrayList<>());
        }

        // Se la lista è vuota, aggiungi una canzone vuota per la modifica
        if (album.getSongs().isEmpty()) {
            album.getSongs().add(new Song());
        }
        model.addAttribute("album", album);
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("artists", artistRepository.findAll());
        return "albums/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Integer id,
            @Valid @ModelAttribute("album") Album albumForm,
            BindingResult bindingResult,
            @RequestParam(value = "action", required = false) String action,
            Model model) {

        Album existingAlbum = albumRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Album not found"));

        // Gestione aggiunta canzone
        if ("addSong".equals(action)) {
            albumForm.getSongs().add(new Song());

            model.addAttribute("album", albumForm);
            model.addAttribute("artists", artistRepository.findAll());
            model.addAttribute("genres", genreRepository.findAll());
            return "albums/edit";
        }

        // Gestione rimozione canzone
        if (action != null && action.startsWith("removeSong")) {
            int index = Integer.parseInt(action.substring("removeSong".length()));
            if (index >= 0 && index < albumForm.getSongs().size()) {
                albumForm.getSongs().remove(index);
            }

            model.addAttribute("album", albumForm);
            model.addAttribute("artists", artistRepository.findAll());
            model.addAttribute("genres", genreRepository.findAll());
            return "albums/edit";
        }

        // Validazione
        if (bindingResult.hasErrors()) {
            model.addAttribute("artists", artistRepository.findAll());
            model.addAttribute("genres", genreRepository.findAll());
            return "albums/edit";
        }

        // Aggiorna proprietà dell'album
        existingAlbum.setTitle(albumForm.getTitle());
        existingAlbum.setDescription(albumForm.getDescription());
        existingAlbum.setReleaseDate(albumForm.getReleaseDate());
        existingAlbum.setCoverUrl(albumForm.getCoverUrl());
        existingAlbum.setGenres(albumForm.getGenres());
        existingAlbum.setArtists(albumForm.getArtists());

        existingAlbum.setSongs(albumForm.getSongs());
        for (Song song : existingAlbum.getSongs()) {
            song.setAlbum(existingAlbum);
            song.setGenres(existingAlbum.getGenres());
            song.setArtists(existingAlbum.getArtists());
        }
        

        albumRepository.save(existingAlbum);
        return "redirect:/albums/" + existingAlbum.getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        albumRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found"));

        albumRepository.deleteById(id);
        return "redirect:/albums";
    }

}
