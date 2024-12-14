package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryArtistRepository {

    List<Artist> artists;

    public InMemoryArtistRepository() {
        artists = Arrays.asList(
                new Artist("Boris", "", "Versatile trio band"),
                new Artist("Cocteau", "Twins", "Dreampop band"),
                new Artist("The Velvet Underground ", "& Nico", "A superband from the sixties with front man Lou Reed"),
                new Artist("my bloody valentine", "", "Mysterious shoegaze band from the nineties"),
                new Artist("A Tribe Called Quest", "", "Rap group")
        );
    }

    public List<Artist> findAll() {
        return artists;
    }

    public Optional<Artist> findById(long id) {
        return artists.stream().filter(artist -> artist.getId() == id).findFirst();
    }
}
