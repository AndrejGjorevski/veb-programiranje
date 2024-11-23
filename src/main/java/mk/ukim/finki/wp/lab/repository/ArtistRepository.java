package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class ArtistRepository {

    List<Artist> artists;

    public ArtistRepository() {
        artists = Arrays.asList(
                new Artist(1L, "Jimi", "Hendrix", "Guitarist, Singer"),
                new Artist(2L, "David", "Bowie", "Singer"),
                new Artist(3L, "Mark", "Knopfler", "Guitarist"),
                new Artist(4L, "Julian", "Casablancas", "Singer"),
                new Artist(5L, "Prince", "", "Singer, Guitarist")
        );
    }

    public List<Artist> findAll() {
        return artists;
    }

    public Optional<Artist> findById(long id) {
        return artists.stream().filter(artist -> artist.getId() == id).findFirst();
    }
}
