package br.com.Rtravel.domaim;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data()
@EqualsAndHashCode(exclude = {"rota", "usuario", "comentario"})
@Entity
public class Comentario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rota_id")
    @JsonIgnore
    private Rota rota;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    private Usuario usuario;

    private String comentario;

    public Comentario(){

    }

    public Comentario(Long id, Rota rota, Usuario usuario, String comentario) {
        super();
        this.id = id;
        this.rota = rota;
        this.usuario = usuario;
        this.comentario = comentario;
    }
}
