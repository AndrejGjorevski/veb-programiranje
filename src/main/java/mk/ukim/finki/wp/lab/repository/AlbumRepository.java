package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Album;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class AlbumRepository {

    List<Album> albums;

    public AlbumRepository() {
        albums = Arrays.asList(
                new Album(1L, "Are You Experienced", "Rock", 1967, new ArrayList<>()),
                new Album(2L, "The Rise and Fall of Ziggy Stardust and the Spiders From Mars", "Rock", 1972, new ArrayList<>()),
                new Album(3L, "Dire Straits", "Rock", 1978, new ArrayList<>()),
                new Album(4L, "Is This It", "Rock", 2001, new ArrayList<>()),
                new Album(5L, "Purple Rain", "Rock", 1984, new ArrayList<>())
        );
    }

    public List<Album> findAll() {
        return albums;
    }

    public Album findById(Long id) {
        return albums.stream().filter(album -> album.getId().equals(id)).findFirst().orElse(null);
    }
}
