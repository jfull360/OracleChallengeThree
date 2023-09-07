package com.alura.foro.services;

import com.alura.foro.entities.Topicos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alura.foro.repositories.TopicosRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author JORGE DOMINGUEZ
 */
@Service
public class TopicosService {

    @Autowired
    private TopicosRepository repository;

    public void saveTopico(Topicos t) { //create // update
        repository.save(t);
    }

    public void deleteTopico(Long id) { //delete
        repository.deleteById(id);
    }
    
    public Optional<Topicos> getTopicById(Long id) {
        return repository.findById(id);
    }
    
    public Page<Topicos> getAllTopics(Integer elements, Integer pagina) { // get ALL 
        return repository.findAll(PageRequest.of(0, 20));
    }
    
    
}
