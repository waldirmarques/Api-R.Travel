package br.com.Rtravel.domaim;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data()
@ToString(exclude = { "rotasOrigem", "rotasDestino" })
@EqualsAndHashCode(exclude = {"rotasOrigem", "rotasDestino", "estado", "nome", "latitude", "longitude"})
@Entity
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@OneToMany(mappedBy="cidadeOrigem")
	private List<Rota> rotasOrigem = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy="cidadeDestino")
	private List<Rota> rotasDestino = new ArrayList<>();

	private String estado;
	private String nome;
	private String latitude;
	private String longitude;
	
	public Cidade() {}

	public Cidade(Long id, String estado, String nome, String latitude, String longitude) {
		super();
		this.id = id;
		this.estado = estado;
		this.nome = nome;
		this.latitude = latitude;
		this.longitude = longitude;
	}

}
