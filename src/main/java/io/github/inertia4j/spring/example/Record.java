package io.github.inertia4j.spring.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String artist;
    private String coverImage; // URL
    private Integer yearOfRelease;

    public Record() {}

    public Record(String name, String artist, String coverImage, Integer yearOfRelease) {
        this.name = name;
        this.artist = artist;
        this.coverImage = coverImage;
        this.yearOfRelease = yearOfRelease;
    }

    public Record(Integer id, String name, String artist, String coverImage, Integer yearOfRelease) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.coverImage = coverImage;
        this.yearOfRelease = yearOfRelease;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public Integer getYearOfRelease() {
        return yearOfRelease;
    }
}
