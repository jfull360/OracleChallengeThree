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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/save")
    public ResponseEntity<?> saveTopico(@RequestBody @Valid Topicos t) {
        Map<String, Object> response = new HashMap<>();
        boolean flag = true;
        String message = "";
        try {
            topicosS.saveTopico(t);
        } catch (Exception e) {
            flag = false;
            message = e.getMessage();
        }
        HttpStatus httpStatus = flag ? HttpStatus.CREATED : HttpStatus.OK;
        response.put("status", httpStatus.value());
        response.put("message", (flag) ? "Topico guardado correctamente"
                : "Ocurrio un problema inesperado al momento de guardar. " + message);
        return new ResponseEntity<>(response, httpStatus);

    }

    @GetMapping("/getAll/page/{pagina}/elements/{elements}")
    public ResponseEntity<?> getAllTopics(@PathVariable("elements") Integer elements, @PathVariable("pagina") Integer pagina) {
        Map<String, Object> response = new HashMap<>();
        Page<Topicos> getAllTopics = topicosS.getAllTopics(elements, pagina);
        response.put("status", (!getAllTopics.isEmpty()) ? HttpStatus.ACCEPTED.value() : HttpStatus.OK.value());
        response.put("data", (!getAllTopics.isEmpty() && getAllTopics.getNumberOfElements() > 0) ? getAllTopics : null);
        if (getAllTopics.isEmpty()) {
            response.put("message", "No existen topicos registrados.");
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{idtopic}")
    public ResponseEntity<?> deleteTopic(@PathVariable("idtopic") Long idTopic) {
        Map<String, Object> response = new HashMap<>();
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
        response.put("message", (flag) ? "Topico eliminado exitosamente."
                : "Ocurrio un problema inesperado al momento de eliminar el topico. " + message);
        return new ResponseEntity<>(response, httpStatus);
    }

}
