package org.lessons.spring.my_music_library.api;

import org.lessons.spring.my_music_library.api.dtos.ArtistDTO;
import org.lessons.spring.my_music_library.models.Artist;
import org.lessons.spring.my_music_library.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/artists")
@CrossOrigin
public class ArtistRestController {

    @Autowired
    private ArtistService artistService;

    @GetMapping
    public ResponseEntity<List<ArtistDTO>> index(@RequestParam(name = "query", required = false) String query) {
        List<Artist> artists = artistService.findAll(query);

        List<ArtistDTO> artistDTOs = artists.stream()
                                           .map(ArtistDTO::fromArtist)
                                           .collect(Collectors.toList());

        return new ResponseEntity<>(artistDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistDTO> show(@PathVariable Integer id) {
        if (!artistService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Artist artist = artistService.findById(id).orElse(null);
        if (artist != null) {
            ArtistDTO artistDTO = ArtistDTO.fromArtist(artist);
            return new ResponseEntity<>(artistDTO, HttpStatus.OK); 
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
