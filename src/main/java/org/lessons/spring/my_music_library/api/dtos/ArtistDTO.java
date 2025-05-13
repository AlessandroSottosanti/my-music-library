package org.lessons.spring.my_music_library.api.dtos;

import java.util.List;
import java.util.stream.Collectors;

import org.lessons.spring.my_music_library.models.Artist;
import org.lessons.spring.my_music_library.models.Song;

public class ArtistDTO {
    private Integer id;
    private String name;
    private String biography;
    private String imageUrl;
    private List<AlbumDTO> albums;
    private List<String> songTitles;

    // Costruttore statico di conversione da Artist
    public static ArtistDTO fromArtist(Artist artist) {
        ArtistDTO dto = new ArtistDTO();
        dto.id = artist.getId();
        dto.name = artist.getName();
        dto.biography = artist.getBiography();
        dto.imageUrl = artist.getImageUrl();
        dto.albums = artist.getAlbums().stream().map(AlbumDTO::fromAlbum).collect(Collectors.toList());
        dto.songTitles = artist.getSongs().stream().map(Song::getTitle).collect(Collectors.toList());
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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<AlbumDTO> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumDTO> albums) {
        this.albums = albums;
    }

    public List<String> getSongTitles() {
        return songTitles;
    }

    public void setSongTitles(List<String> songTitles) {
        this.songTitles = songTitles;
    }
}

