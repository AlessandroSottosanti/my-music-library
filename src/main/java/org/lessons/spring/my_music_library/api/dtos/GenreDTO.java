package org.lessons.spring.my_music_library.api.dtos;
import java.util.List;
import java.util.stream.Collectors;

import org.lessons.spring.my_music_library.models.Album;
import org.lessons.spring.my_music_library.models.Genre;
import org.lessons.spring.my_music_library.models.Song;

public class GenreDTO {
    private Integer id;
    private String name;
    private List<String> albumTitles;
    private List<String> songTitles;

    // Costruttore statico di conversione da Genre
    public static GenreDTO fromGenre(Genre genre) {
        GenreDTO dto = new GenreDTO();
        dto.id = genre.getId();
        dto.name = genre.getName();
        dto.albumTitles = genre.getAlbums().stream().map(Album::getTitle).collect(Collectors.toList());
        dto.songTitles = genre.getSongs().stream().map(Song::getTitle).collect(Collectors.toList());
        return dto;
    }

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAlbumTitles() {
        return albumTitles;
    }

    public void setAlbumTitles(List<String> albumTitles) {
        this.albumTitles = albumTitles;
    }

    public List<String> getSongTitles() {
        return songTitles;
    }

    public void setSongTitles(List<String> songTitles) {
        this.songTitles = songTitles;
    }
}
