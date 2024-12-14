package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.InMemorySongRepository;
import mk.ukim.finki.wp.lab.repository.jpa.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.jpa.ArtistRepository;
import mk.ukim.finki.wp.lab.repository.jpa.SongRepository;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songs;
    private final AlbumRepository albums;
    private final ArtistRepository artists;

    public SongServiceImpl(SongRepository songs, AlbumRepository albums, ArtistRepository artists) {
        this.songs = songs;
        this.albums = albums;
        this.artists = artists;
    }


    @Override
    public List<Song> findByAlbum_Id(long albumId) {
        return songs.findByAlbum_Id(albumId);
    }

    @Override
    public List<Song> listSongs() {
        return songs.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        song.getPerformers().add(artist);
        artist.getSongs().add(song);
        songs.save(song);
        artists.save(artist);
        return artist;
    }

    @Override
    public Song create(String trackId, String title, String genre, int releaseYear, List<Artist> artists, Long albumId) {
        Album album = albums.findById(albumId).get();
        Song song = new Song(trackId, title, genre, releaseYear, artists, album);
        return songs.save(song);
    }

    @Override
    public Song updateSong(Long songId, String trackId, String title, String genre, int releaseYear, Long albumId) {
        Album album = albums.findById(albumId).get();
        Song song = songs.findById(songId).get();
        song.setTitle(title);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setAlbum(album);
        return songs.save(song);
    }

    @Override
    public Optional<Song> findBySongId(Long songId) {
        return songs.findById(songId);
    }

    @Override
    public void deleteSong(Long songId) {
        songs.deleteById(songId);
    }


}
