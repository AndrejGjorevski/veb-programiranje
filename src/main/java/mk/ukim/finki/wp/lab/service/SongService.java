package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {

    List<Song> findByAlbum_Id(long albumId);

    List<Song> listSongs();

    //Artist addArtistToSong(Artist artist, Song song);

    //Song findByTrackId(String trackId);

    Song create(String trackId, String title, String genre, int releaseYear, List<Artist> artists, Long albumId);

    Song updateSong(Long songId, String trackId, String title, String genre, int releaseYear, Long albumId);

    Optional<Song> findBySongId(Long songId);

    void deleteSong(Long songId);
}
