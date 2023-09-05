package com.alura.foro.entities;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 *
 * @author JORGE DOMINGUEZ
 */
@Entity
@Table(name = "topicos", catalog = "foro", schema = "")
@Data
public class Topicos implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @Column(name = "mensaje")
    private String mensaje;
    @Basic(optional = false)
    @Column(name = "fecha_creacion")
    private String fechaCreacion;
    @Basic(optional = false)
    @Column(name = "estatus")
    private String estatus;
    @Basic(optional = false)
    @Column(name = "autor")
    private String autor;
    @Basic(optional = false)
    @Column(name = "curso")
    private String curso;

}
