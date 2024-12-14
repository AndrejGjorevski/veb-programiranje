package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SongDetailsController {

    private final ArtistService artistService;
    private final SongService songService;

    public SongDetailsController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @GetMapping("/songDetails")
    public String getSongDetailsPage(HttpSession session, String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        Long trackId = (Long) session.getAttribute("trackId");
        Song song = songService.findBySongId(trackId).get();
        model.addAttribute("song", song);
        return "songDetails";
    }

    @PostMapping("/songs/{songId}/artists/{artistId}")
    public String removeArtistFromSong(@PathVariable Long songId, @PathVariable Long artistId, HttpSession session, String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        Long trackId = (Long) session.getAttribute("trackId");
        Song song = songService.findBySongId(trackId).get();
        Artist artist = artistService.findById(artistId).get();
        songService.deleteArtistFromSong(artist, song);
        return "redirect:/songDetails";
    }
}
