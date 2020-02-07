package br.com.Rtravel.domaim;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	public Double getDoubleitude() {
		return longitude;
	}
	public void setDoubleitude(Double longitude) {
		this.longitude = longitude;
	}

}
