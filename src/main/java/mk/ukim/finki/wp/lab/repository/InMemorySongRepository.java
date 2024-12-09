package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemorySongRepository {

    List<Song> songs;

    public List<Song> findAll() {
        return DataHolder.songs;
    }

    public Song findByTrackId(String trackId) {
        return DataHolder.songs.stream().filter(s -> s.getTrackId().equals(trackId)).findFirst().orElse(null);
    }

    public Artist addArtistToSong(Artist artist, Song song) {
        //song.getPerformers().add(artist);
        return artist;
    }

    public void addSongToList(Song song) {
        DataHolder.songs.add(song);
    }

    public void updateSong(Song song) {
        DataHolder.songs.stream()
                .filter(s -> s.getId().equals(song.getId()))
                .findFirst()
                .ifPresent(existingSong -> {
                    existingSong.setTitle(song.getTitle());
                    existingSong.setGenre(song.getGenre());
                    existingSong.setReleaseYear(song.getReleaseYear());
                    existingSong.setAlbum(song.getAlbum());
                });

    }

    public Optional<Song> findBySongId(Long songId) {
        return DataHolder.songs.stream().filter(s -> s.getId().equals(songId)).findFirst();
    }

    public void deleteSong(Long songId) {
        DataHolder.songs.removeIf(s -> s.getId().equals(songId));
    }
}
