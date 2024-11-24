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
                new Album(1L, "Boris at Last -Feedbacker-", "Stoner Rock", 2003, new ArrayList<>()),
                new Album(2L, "Heaven or Las Vegas", "Dreampop", 1990, new ArrayList<>()),
                new Album(3L, "The Velvet Underground & Nico", "Rock", 1967, new ArrayList<>()),
                new Album(4L, "Loveless", "Shoegaze", 1991, new ArrayList<>()),
                new Album(5L, "The Low End Theory", "Rap", 1991, new ArrayList<>())
        );
    }

    public List<Album> findAll() {
        return albums;
    }

    public Album findById(Long id) {
        return albums.stream().filter(album -> album.getId().equals(id)).findFirst().orElse(null);
    }
}
