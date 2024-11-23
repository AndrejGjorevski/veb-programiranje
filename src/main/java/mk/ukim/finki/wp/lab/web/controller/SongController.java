package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SongController {

    private final SongService songService;
    private final AlbumService albumService;
    private final ArtistService artistService;

    public SongController(SongService songService, AlbumService albumService, ArtistService artistService) {
        this.songService = songService;
        this.albumService = albumService;
        this.artistService = artistService;
    }

    @GetMapping("/songs")
    public String getSongsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("bodyContent", "songs");
        model.addAttribute("songs", this.songService.listSongs());
        return "listSongs";
    }

    @GetMapping("/add-form")
    public String getAddSongPage(Model model) {
        List<Song> songs = this.songService.listSongs();
        List<Album> albums = this.albumService.findAll();
        model.addAttribute("songs", songs);
        model.addAttribute("albums", albums);
        return "add-song";
    }

    @PostMapping("/add")
    public String saveSong(@RequestParam String trackId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam int releaseYear,
                           @RequestParam Long albumId) {

        Album album = albumService.findById(albumId);
        this.songService.addSongToList(new Song(trackId, title, genre, releaseYear, new ArrayList<>(), album));
        return "redirect:/songs";
    }

    @GetMapping("/edit-form/{songId}")
    public String getEditSongForm(@PathVariable Long songId,  Model model) {
        if (this.songService.findBySongId(songId).isPresent()) {
            Song song = this.songService.findBySongId(songId).get();
            List<Song> songs = this.songService.listSongs();
            List<Album> albums = this.albumService.findAll();
            model.addAttribute("songs", songs);
            model.addAttribute("albums", albums);
            model.addAttribute("song", song);
            return "add-song";
        }
        return "redirect:/songs?error=SongNotFound";
    }

    @PostMapping("/edit/{songId}")
    public String editSong(@PathVariable Long songId,
                           @RequestParam String trackId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam int releaseYear,
                           @RequestParam Long albumId) {
        Album album = albumService.findById(albumId);
        songService.updateSong(songId, trackId, title, genre, releaseYear, album);
        return "redirect:/songs";
    }

    @PostMapping("/delete/{songId}")
    public String deleteSong(@PathVariable Long songId) {
        songService.deleteSong(songId);
        return "redirect:/songs";
    }

    @PostMapping("/submit")
    public String addTrackIdToSession(@RequestParam String trackId, HttpSession session) {
        session.setAttribute("trackId", trackId);
        return "redirect:/artists";
    }
}
