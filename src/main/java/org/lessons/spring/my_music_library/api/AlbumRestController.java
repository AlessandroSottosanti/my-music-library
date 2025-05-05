package org.lessons.spring.my_music_library.api;

import org.lessons.spring.my_music_library.api.dtos.AlbumDTO;
import org.lessons.spring.my_music_library.models.Album;
import org.lessons.spring.my_music_library.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/albums")
@CrossOrigin
public class AlbumRestController {

    @Autowired
    private AlbumService albumService;

    @GetMapping
    public ResponseEntity<List<AlbumDTO>> index(@RequestParam(name = "query", required = false) String query) {
        List<Album> albums = albumService.findAll(query);

        List<AlbumDTO> albumDTOs = albums.stream()
                                        .map(AlbumDTO::fromAlbum)
                                        .collect(Collectors.toList());

        return new ResponseEntity<>(albumDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDTO> show(@PathVariable Integer id) {
        if (!albumService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }

        Album album = albumService.findById(id).orElse(null);
        if (album != null) {
            AlbumDTO albumDTO = AlbumDTO.fromAlbum(album);
            return new ResponseEntity<>(albumDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);  
    }
}
