package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.repository.AlbumRepository;
import mk.ukim.finki.wp.lab.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albums;

    public AlbumServiceImpl(AlbumRepository albums) {
        this.albums = albums;
    }

    @Override
    public List<Album> findAll() {
        return albums.findAll();
    }

    @Override
    public Album findById(Long id) {
        return albums.findById(id);
    }
}