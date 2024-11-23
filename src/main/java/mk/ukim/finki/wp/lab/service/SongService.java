package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {

    List<Song> listSongs();

    Artist addArtistToSong(Artist artist, Song song);

    Song findByTrackId(String trackId);

    void addSongToList(Song song);

    void updateSong(Long songId, String trackId, String title, String genre, int releaseYear, Album album);

    Optional<Song> findBySongId(Long songId);

    void deleteSong(Long songId);
}
