package org.lessons.spring.my_music_library.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il nome dell'artista è obbligatorio")
    @Size(max = 100, message = "Il nome dell'artista non può superare i 100 caratteri")
    private String name;

    @Lob
    private String biography;

    @Column(name = "image_url")
    @Size(max = 255, message = "L'URL dell'immagine è troppo lungo")
    @Pattern(regexp = "^(http|https)://.*", message = "Inserisci un URL valido per l'immagine", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String imageUrl;

    @ManyToMany
    @JsonManagedReference
    @JoinTable( 
        name = "album_artist",
        joinColumns = @JoinColumn(name = "artist_id"),
        inverseJoinColumns = @JoinColumn(name = "album_id")
        )
    private List<Album> albums;

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

    public List<Album> getAlbums() {
        return this.albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

}
