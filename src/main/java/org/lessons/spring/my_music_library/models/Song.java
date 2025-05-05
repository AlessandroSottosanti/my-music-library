package org.lessons.spring.my_music_library.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il titolo è obbligatorio")
    @Size(max = 100, message = "Il titolo non può superare i 100 caratteri")
    private String title;

    @ManyToMany
    @JoinTable(name = "song_artist", joinColumns = @JoinColumn(name = "song_id"), inverseJoinColumns = @JoinColumn(name = "artist_id"))
    @NotEmpty(message = "Seleziona almeno un artista")
    private List<Artist> artists;

    @NotNull(message = "La durata è obbligatoria")
    @Min(value = 10, message = "La durata deve essere di almeno 10 secondi")
    @Max(value = 900, message = "La durata non può superare i 15 minuti (900 secondi)")
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "album_id")
    @JsonIgnoreProperties({ "artists", "songs", "genres" })
    private Album album;

    @ManyToMany
    @JoinTable(name = "song_genre", joinColumns = @JoinColumn(name = "song_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    // Getter e Setter

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Album getAlbum() {
        return this.album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<Genre> getGenres() {
        return this.genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public String getGenresAsString() {
        if (genres == null || genres.isEmpty()) {
            return "";
        }
        return genres.toString().replaceAll("[\\[\\]]", "");
    }

}
