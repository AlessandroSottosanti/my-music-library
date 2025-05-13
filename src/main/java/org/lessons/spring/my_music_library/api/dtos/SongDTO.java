package org.lessons.spring.my_music_library.api.dtos;
import java.time.LocalDate;
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
    private String albumCoverUrl;
    private LocalDate albumReleaseDate;
    private List<String> artistNames;
    private List<String> genreNames;
    private String songUrlYt;

    // Costruttore statico di conversione da Song
    public static SongDTO fromSong(Song song) {
        SongDTO dto = new SongDTO();
        dto.id = song.getId();
        dto.title = song.getTitle();
        dto.duration = song.getDuration();
        dto.songUrlYt = song.getSongUrlYt();
        dto.albumTitle = song.getAlbum() != null ? song.getAlbum().getTitle() : null;
        dto.albumCoverUrl = song.getAlbum() != null ? song.getAlbum().getCoverUrl() : null;
        dto.albumReleaseDate = song.getAlbum() != null ? song.getAlbum().getReleaseDate() : null;
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

    public String getSongUrlYt() {
        return songUrlYt;
    }

    public void setSongUrlYt(String songUrlYt) {
        this.songUrlYt = songUrlYt;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getAlbumCoverUrl() {
        return albumCoverUrl;
    }

    public void setAlbumCoverUrl(String albumCoverUrl) {
        this.albumCoverUrl = albumCoverUrl;
    }

    public LocalDate getAlbumReleaseDate() {
        return albumReleaseDate;
    }

    public void setAlbumReleaseDate(LocalDate albumReleaseDate) {
        this.albumReleaseDate = albumReleaseDate;
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
