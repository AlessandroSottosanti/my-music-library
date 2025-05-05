package org.lessons.spring.my_music_library.api.dtos;
import java.util.List;
import java.util.stream.Collectors;

import org.lessons.spring.my_music_library.models.Album;
import org.lessons.spring.my_music_library.models.Artist;
import org.lessons.spring.my_music_library.models.Genre;

public class AlbumDTO {
    private Integer id;
    private String title;
    private String description;
    private String releaseDate;
    private String coverUrl;
    private List<String> artistNames;
    private List<String> genreNames;

    // Costruttore statico di conversione da Album
    public static AlbumDTO fromAlbum(Album album) {
        AlbumDTO dto = new AlbumDTO();
        dto.id = album.getId();
        dto.title = album.getTitle();
        dto.description = album.getDescription();
        dto.releaseDate = album.getReleaseDate() != null ? album.getReleaseDate().toString() : null;
        dto.coverUrl = album.getCoverUrl();
        dto.artistNames = album.getArtists().stream().map(Artist::getName).collect(Collectors.toList());
        dto.genreNames = album.getGenres().stream().map(Genre::toString).collect(Collectors.toList());
        return dto;
    }

    // Getters & Setters
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
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
