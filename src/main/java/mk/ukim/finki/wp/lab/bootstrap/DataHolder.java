package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.jpa.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.jpa.ArtistRepository;
import mk.ukim.finki.wp.lab.repository.jpa.SongRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Song> songs = null;
    public static List<Album> albums = null;
    public static List<Artist> artists = null;

    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    public DataHolder(SongRepository songRepository, AlbumRepository albumRepository, ArtistRepository artistRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }

    @PostConstruct
    public void init() {
        albums = new ArrayList<>();
        if (this.albumRepository.count() == 0) {
            albums.add(new Album("Boris at Last -Feedbacker-", "Stoner Rock", 2003, new ArrayList<>()));
            albums.add(new Album("Heaven or Las Vegas", "Dreampop", 1990, new ArrayList<>()));
            albums.add(new Album("The Velvet Underground & Nico", "Rock", 1967, new ArrayList<>()));
            albums.add(new Album("Loveless", "Shoegaze", 1991, new ArrayList<>()));
            albums.add(new Album("The Low End Theory", "Rap", 1991, new ArrayList<>()));
            albumRepository.saveAll(albums);
        }

        songs = new ArrayList<>();
        if (this.songRepository.count() == 0) {
            songs.add(new Song("1", "Feedbacker I-V", "Stoner Rock", 2003, new ArrayList<>(), albums.get(0)));
            songs.add(new Song("2", "Cherry-coloured Funk", "Dreampop", 1990, new ArrayList<>(), albums.get(1)));
            songs.add(new Song("3", "I'm Waiting For The Man", "Rock", 1967, new ArrayList<>(), albums.get(2)));
            songs.add(new Song("4", "sometimes", "Shoegaze", 1991, new ArrayList<>(), albums.get(3)));
            songs.add(new Song("5", "Jazz(We've Got It)", "Rap", 1991, new ArrayList<>(), albums.get(4)));
            songRepository.saveAll(songs);
        }

        artists = new ArrayList<>();
        if (this.artistRepository.count() == 0) {
            artists.add(new Artist("Boris", "", "Versatile trio band"));
            artists.add(new Artist("Cocteau", "Twins", "Dreampop band"));
            artists.add(new Artist("The Velvet Underground ", "& Nico", "A band from the sixties with front man Lou Reed"));
            artists.add(new Artist("my bloody valentine", "", "Mysterious shoegaze band from the nineties"));
            artists.add(new Artist("A Tribe Called Quest", "", "Rap group"));
            artistRepository.saveAll(artists);
        }
    }
}
