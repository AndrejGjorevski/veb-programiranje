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
                new Artist(1L, "Boris", "", "Versatile trio band"),
                new Artist(2L, "Cocteau", "Twins", "Dreampop band"),
                new Artist(3L, "The Velvet Underground ", "& Nico", "A superband from the sixties with front man Lou Reed"),
                new Artist(4L, "my bloody valentine", "", "Mysterious shoegaze band from the nineties"),
                new Artist(5L, "A Tribe Called Quest", "", "Rap group")
        );
    }

    public List<Artist> findAll() {
        return artists;
    }

    public Optional<Artist> findById(long id) {
        return artists.stream().filter(artist -> artist.getId() == id).findFirst();
    }
}
