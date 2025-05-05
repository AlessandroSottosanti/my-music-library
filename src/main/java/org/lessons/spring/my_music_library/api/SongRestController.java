package org.lessons.spring.my_music_library.api;

import java.util.List;
import java.util.stream.Collectors;

import org.lessons.spring.my_music_library.api.dtos.SongDTO;
import org.lessons.spring.my_music_library.models.Song;
import org.lessons.spring.my_music_library.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/songs")
public class SongRestController {

    @Autowired
    SongService songService;

    @GetMapping
    public ResponseEntity<List<SongDTO>> index(@RequestParam(name = "query", required = false) String query) {
        List<Song> songs = songService.findAll(query);

        // Restituisci la lista delle canzoni come DTO
        List<SongDTO> songDTOs = songs.stream()
                                        .map(SongDTO::fromSong)
                                        .collect(Collectors.toList());

        return new ResponseEntity<List<SongDTO>>(songDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongDTO> show(@PathVariable Integer id) {
        if (!songService.existsById(id)) {
            return new ResponseEntity<SongDTO>(HttpStatus.NOT_FOUND);  
        }

        // Ottieni la canzone dal service e trasformala in DTO
        Song song = songService.getById(id).orElse(null);
        if (song != null) {
            SongDTO songDTO = SongDTO.fromSong(song);
            return new ResponseEntity<SongDTO>(songDTO, HttpStatus.OK);
        }

        return new ResponseEntity<SongDTO>(HttpStatus.NOT_FOUND); 
    }
}
