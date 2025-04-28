package org.lessons.spring.my_music_library.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name; // nome del genere, es. "Pop", "Rock", "Jazz"

    // Relazione ManyToMany con Song
    @ManyToMany(mappedBy = "genres")
    private List<Song> songs;

    // Relazione ManyToMany con Album
    @ManyToMany(mappedBy = "genres")
    private List<Album> albums;

    // Getter e Setter
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return this.songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<Album> getAlbums() {
        return this.albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return name;
    }

}
