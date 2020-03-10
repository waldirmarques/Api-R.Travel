package br.com.Rtravel.domaim;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data()
@EqualsAndHashCode(exclude = { "usuario", "comentario" })
@Entity
public class Comentario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String comentario;

    public Comentario() {

    }

    public Comentario(Long id, Usuario usuario, String comentario) {
        super();
        this.id = id;
        this.usuario = usuario;
        this.comentario = comentario;
    }
}
