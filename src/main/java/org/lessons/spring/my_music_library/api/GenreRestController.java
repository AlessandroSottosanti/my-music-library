package org.lessons.spring.my_music_library.api;

import org.lessons.spring.my_music_library.api.dtos.GenreDTO;
import org.lessons.spring.my_music_library.models.Genre;
import org.lessons.spring.my_music_library.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/genres")
@CrossOrigin
public class GenreRestController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDTO>> index(@RequestParam(name = "query", required = false) String query) {
        List<Genre> genres = genreService.findAll(query);

        List<GenreDTO> genreDTOs = genres.stream()
                                         .map(GenreDTO::fromGenre)
                                         .collect(Collectors.toList());

        return new ResponseEntity<>(genreDTOs, HttpStatus.OK);  // Status 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> show(@PathVariable Integer id) {
        if (!genreService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }

        Genre genre = genreService.findById(id).orElse(null);
        if (genre != null) {
            GenreDTO genreDTO = GenreDTO.fromGenre(genre);
            return new ResponseEntity<>(genreDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
    }
}
