package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findByAlbum_Id(Long albumId);

    List<Song> findAll();

    Song save(Song song);

    Optional<Song> findById(Long id);

    void deleteById(Long id);
}
