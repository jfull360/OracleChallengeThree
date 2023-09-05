package com.alura.foro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alura.foro.repositories.TopicosRepository;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author JORGE DOMINGUEZ
 */
@Service
public class TopicosService {

    @Autowired
    private final TopicosRepository repository;

    public TopicosService(TopicosRepository r) {
        this.repository = r;
    }

    public void deleteHuesped(Long id) {
        repository.deleteById(id);
    }
}
