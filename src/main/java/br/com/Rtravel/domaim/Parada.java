package br.com.Rtravel.domaim;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Parada implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String pontoReferencia;
	private String url;
	private Double latitude;
	private Double longitude;

	@JsonIgnore
	@ManyToMany(mappedBy = "paradas", fetch = FetchType.EAGER)
	private List<Rota> rotas;

	private Date horarioChegada;

	public Parada() {}
	
	
	public Parada(Long id, String pontoReferencia, String url, Double latitude, Double longitude) {
		super();
		this.id = id;
		this.pontoReferencia = pontoReferencia;
		this.url = url;
		this.latitude = latitude;
		this.longitude = longitude;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getPontoReferencia() {
		return pontoReferencia;
	}


	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
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

	public List<Rota> getRotas() {
		return rotas;
	}


	public void setRotas(List<Rota> rotas) {
		this.rotas = rotas;
	}


	public Date getHorarioChegada() {
		return horarioChegada;
	}


	public void setHorarioChegada(Date horarioChegada) {
		this.horarioChegada = horarioChegada;
	}

}
