package br.com.Rtravel.domaim;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@OneToMany(mappedBy="cidadeOrigem")
	private List<Rota> rotasOrigem = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy="cidadeDestino")
	private List<Rota> rotasDestino = new ArrayList<>();

	private String estado;
	private String nome;
	private Double latitude;
	private Double longitude;
	
	public Cidade() {}
	
	public Cidade(Long id, String estado, String nome, Double latitude, Double longitude) {
		super();
		this.id = id;
		this.estado = estado;
		this.nome = nome;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Rota> getRotasOrigem() {
		return rotasOrigem;
	}
	public void setRotasOrigem(List<Rota> rotasOrigem) {
		this.rotasOrigem = rotasOrigem;
	}

	public List<Rota> getRotasDestino() {
		return rotasDestino;
	}

	public void setRotasDestino(List<Rota> rotasDestino) {
		this.rotasDestino = rotasDestino;
	}

	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Cidade{" +
				"id=" + id +
				", estado='" + estado + '\'' +
				", nome='" + nome + '\'' +
				", latitude=" + latitude +
				", longitude=" + longitude +
				'}';
	}
}
