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
	@ManyToMany(mappedBy="cidades", fetch = FetchType.EAGER)
	private List<Rota> rotas = new ArrayList<>();

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
	public List<Rota> getRotas() {
		return rotas;
	}
	public void setRotas(List<Rota> rotas) {
		this.rotas = rotas;
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
	public Double getDoubleitude() {
		return longitude;
	}
	public void setDoubleitude(Double longitude) {
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
