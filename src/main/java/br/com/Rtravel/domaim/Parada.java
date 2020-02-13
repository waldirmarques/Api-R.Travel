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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data()
@ToString(exclude = { "" })
@EqualsAndHashCode(exclude = {"pontoReferencia", "url", "latitude", "longitude", "rotas", "horarioChegada"})
@Entity
public class Parada implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String pontoReferencia;
	private String url;
	private String latitude;
	private String longitude;

	@JsonIgnore
	@ManyToMany(mappedBy = "paradas")
	private List<Rota> rotas;

	private Date horarioChegada;

	public Parada() {}

	public Parada(Long id, String pontoReferencia, String url, String latitude, String longitude) {
		super();
		this.id = id;
		this.pontoReferencia = pontoReferencia;
		this.url = url;
		this.latitude = latitude;
		this.longitude = longitude;
	}

}
