package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ArtistController {

    private final ArtistService artistService;
    private final SongService songService;

    public ArtistController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @GetMapping("/artists")
    public String getArtistPage(HttpSession session, String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String trackId = (String) session.getAttribute("trackId");

        model.addAttribute("trackId", trackId);
        model.addAttribute("artists", this.artistService.findAll());
        return "artistsList";
    }

    @PostMapping("/addArtist")
    public String addArtistToSong(@RequestParam Long artistId, HttpSession session) {
        Long trackId = Long.valueOf((String) session.getAttribute("trackId"));
        Song song = songService.findBySongId(trackId).get();
        Optional<Artist> artist = artistService.findById(artistId);
        songService.addArtistToSong(artist.get(), song);

        return "redirect:/songDetails";
    }
}
