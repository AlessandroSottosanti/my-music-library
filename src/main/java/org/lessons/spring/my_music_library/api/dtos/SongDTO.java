package org.lessons.spring.my_music_library.api.dtos;
import java.util.List;
import java.util.stream.Collectors;

import org.lessons.spring.my_music_library.models.Artist;
import org.lessons.spring.my_music_library.models.Genre;
import org.lessons.spring.my_music_library.models.Song;

public class SongDTO {
    private Integer id;
    private String title;
    private Integer duration;
    private String albumTitle;
    private List<String> artistNames;
    private List<String> genreNames;

    // Costruttore statico di conversione da Song
    public static SongDTO fromSong(Song song) {
        SongDTO dto = new SongDTO();
        dto.id = song.getId();
        dto.title = song.getTitle();
        dto.duration = song.getDuration();
        dto.albumTitle = song.getAlbum() != null ? song.getAlbum().getTitle() : null;
        dto.artistNames = song.getArtists().stream().map(Artist::getName).collect(Collectors.toList());
        dto.genreNames = song.getGenres().stream().map(Genre::toString).collect(Collectors.toList()); // o getName()
        return dto;
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public List<String> getArtistNames() {
        return artistNames;
    }

    public void setArtistNames(List<String> artistNames) {
        this.artistNames = artistNames;
    }

    public List<String> getGenreNames() {
        return genreNames;
    }

    public void setGenreNames(List<String> genreNames) {
        this.genreNames = genreNames;
    }
}
