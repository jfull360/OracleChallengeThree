/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.foro.controllers;

import com.alura.foro.entities.Topicos;
import com.alura.foro.services.TopicosService;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JORGE DOMINGUEZ
 */
@RestController
@RequestMapping("/topicos")
@CrossOrigin
public class TopicosController {

    @Autowired
    TopicosService topicosS;
    Map<String, Object> response = new HashMap<>();
    String foundMessage = "Se encontro un topico con titulo o mensaje repetido.";
    String errorMessage = "Ocurrió un problema inesperado al momento de ";

    @PostMapping("/save")
    public ResponseEntity<?> saveTopico(@RequestBody @Valid Topicos t) {
        response.clear();
        String message = "";
        boolean flag = topicosS.checkIfExistsByTitleMessage(t.getTitulo(), t.getMensaje());
        if (flag) {
            try {
                topicosS.saveTopico(t);
            } catch (Exception e) {
                flag = false;
                message = e.getMessage();
            }
        }
        HttpStatus httpStatus = flag ? HttpStatus.CREATED : HttpStatus.OK;
        response.put("status", httpStatus.value());
        response.put("message", (flag) ? "Tópico guardado correctamente"
                : errorMessage + "guardar." + " O " + foundMessage + message);
        return new ResponseEntity<>(response, httpStatus);

    }

    @GetMapping("/getAll/page/{pagina}/elements/{elements}")
    public ResponseEntity<?> getAllTopics(@PathVariable("elements") Integer elements, @PathVariable("pagina") Integer pagina) {
        response.clear();
        Page<Topicos> getAllTopics = topicosS.getAllTopics(elements, pagina);
        response.put("status", (!getAllTopics.isEmpty()) ? HttpStatus.ACCEPTED.value() : HttpStatus.OK.value());
        response.put("data", (!getAllTopics.isEmpty() && getAllTopics.getNumberOfElements() > 0) ? getAllTopics : null);
        if (getAllTopics.isEmpty()) {
            response.put("message", "No existen tópicos registrados.");
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/gettopicbyid/{idtopic}")
    public ResponseEntity<?> getTopicById(@PathVariable("idtopic") Long idTopic) {
        response.clear();
        Optional<Topicos> getSpecificTopic = topicosS.getTopicById(idTopic);
        response.put("status", (!getSpecificTopic.isEmpty()) ? HttpStatus.ACCEPTED.value() : HttpStatus.OK.value());
        response.put("data", (!getSpecificTopic.isEmpty()) ? getSpecificTopic : null);
        if (getSpecificTopic.isEmpty()) {
            response.put("message", "No existe un tópico registrado con ese ID.");
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{idtopic}")
    public ResponseEntity<?> updateTopicById(@PathVariable("idtopic") Long idTopic, @RequestBody @Valid Topicos t) {
        response.clear();
        boolean flag = topicosS.checkIfExistsByTitleMessageUpdate(idTopic,t.getTitulo(), t.getMensaje());
        String message = "";
        Optional<Topicos> topicFound = topicosS.getTopicById(idTopic);
        if (topicFound.isEmpty()) {
            message = "No se pudo actualizar, debido a que no se encontró un tópico con ese ID.";
            flag = false;
        } else if (flag) {
            try {
                t.setId(topicFound.get().getId());
                topicosS.saveTopico(t);
            } catch (Exception e) {
                flag = false;
                message = e.getMessage();
            }
        }
        HttpStatus httpStatus = flag ? HttpStatus.CREATED : HttpStatus.OK;
        response.put("status", httpStatus.value());
        response.put("message", (flag) ? "Tópico actualizado correctamente"
                : errorMessage + "actualizar." + " O " + foundMessage + message);
        return new ResponseEntity<>(response, httpStatus);
    }

    @DeleteMapping("/delete/{idtopic}")
    public ResponseEntity<?> deleteTopic(@PathVariable("idtopic") Long idTopic) {
        response.clear();
        boolean flag = true;
        String message = "";
        try {
            topicosS.deleteTopico(idTopic);
        } catch (Exception e) {
            flag = false;
            message = e.getMessage();
        }
        HttpStatus httpStatus = flag ? HttpStatus.CREATED : HttpStatus.OK;
        response.put("status", httpStatus.value());
        response.put("message", (flag) ? "Tópico eliminado exitosamente."
                : errorMessage + " eliminar el tópico . " + message);
        return new ResponseEntity<>(response, httpStatus);
    }

}
