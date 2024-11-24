package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class SongRepository {

    List<Song> songs;

    public SongRepository(AlbumRepository albumRepository) {
        songs = new ArrayList<>();
        songs.add(new Song("1", "Feedbacker I-V", "Stoner Rock", 2003, new ArrayList<>(), albumRepository.albums.get(0)));
        songs.add(new Song("2", "Cherry-coloured Funk", "Dreampop", 1990, new ArrayList<>(), albumRepository.albums.get(1)));
        songs.add(new Song("3", "I'm Waiting For The Man", "Rock", 1967, new ArrayList<>(), albumRepository.albums.get(2)));
        songs.add(new Song("4", "sometimes", "Shoegaze", 1991, new ArrayList<>(), albumRepository.albums.get(3)));
        songs.add(new Song("5", "Jazz(We've Got It)", "Rap", 1991, new ArrayList<>(), albumRepository.albums.get(4)));
    }

    public List<Song> findAll() {
        return songs;
    }

    public Song findByTrackId(String trackId) {
        return songs.stream().filter(s -> s.getTrackId().equals(trackId)).findFirst().orElse(null);
    }

    public Artist addArtistToSong(Artist artist, Song song) {
        song.getPerformers().add(artist);
        return artist;
    }

    public void addSongToList(Song song) {
        songs.add(song);
    }

    public void updateSong(Song song) {
        songs.stream()
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
        return songs.stream().filter(s -> s.getId().equals(songId)).findFirst();
    }

    public void deleteSong(Long songId) {
        songs.removeIf(s -> s.getId().equals(songId));
    }
}
