package org.lessons.spring.my_music_library.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il titolo dell' album è obbligatorio")
    @Size(max = 100, message = "Il titolo dell'album non può superare i 100 caratteri")
    private String title;

    @Size(max = 255, message = "La descrizione è troppo lunga")
    private String description;

    @Column(name = "release_date")
    @PastOrPresent(message = "La data di rilascio non può essere nel futuro")
    private LocalDate releaseDate;

    @Column(name = "cover_url")
    private String coverUrl;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL) 
    @JsonIgnore
    @Valid
    private List<Song> songs;

    @ManyToMany
    @JoinTable(
        name = "album_artist",
        joinColumns = @JoinColumn(name = "album_id"),
        inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    @NotEmpty(message = "Devi selezionare almeno un artista per l' album")
    @JsonIgnoreProperties({"albums", "songs"})
    private List<Artist> artists;
    

    @ManyToMany
    @JoinTable(
        name = "album_genre", 
        joinColumns = @JoinColumn(name = "album_id"), 
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

    // Getters & Setters
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCoverUrl() {
        return this.coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public List<Song> getSongs() {
        return this.songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<Artist> getArtists() {
        return this.artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public List<Genre> getGenres() {
        return this.genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getArtistsAsString() {
        if (artists == null || artists.isEmpty()) {
            return "";
        }
        return artists.toString().replaceAll("[\\[\\]]", "");
    }
    
}
