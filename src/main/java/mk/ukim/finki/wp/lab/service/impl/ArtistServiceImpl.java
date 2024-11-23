package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.repository.ArtistRepository;
import mk.ukim.finki.wp.lab.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artists;

    public ArtistServiceImpl(ArtistRepository artists) {
        this.artists = artists;
    }

    @Override
    public List<Artist> listArtists() {
        return artists.findAll();
    }

    @Override
    public Optional<Artist> findArtistById(Long id) {
        return artists.findById(id);
    }
}
