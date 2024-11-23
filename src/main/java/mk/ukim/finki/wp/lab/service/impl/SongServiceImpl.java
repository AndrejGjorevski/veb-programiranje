package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.SongRepository;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songs;

    public SongServiceImpl(SongRepository songs) {
        this.songs = songs;
    }


    @Override
    public List<Song> listSongs() {
        return songs.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        return songs.addArtistToSong(artist, song);
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songs.findByTrackId(trackId);
    }

    @Override
    public void addSongToList(Song song) {
        songs.addSongToList(song);
    }

    @Override
    public void updateSong(Long songId, String trackId, String title, String genre, int releaseYear, Album album) {
        Song song = songs.findBySongId(songId).get();
        song.setTitle(title);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setAlbum(album);
        songs.updateSong(song);
    }

    @Override
    public Optional<Song> findBySongId(Long songId) {
        return songs.findBySongId(songId);
    }

    @Override
    public void deleteSong(Long songId) {
        songs.deleteSong(songId);
    }


}
