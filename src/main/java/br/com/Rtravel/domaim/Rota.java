package br.com.Rtravel.domaim;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Data()
@ToString(exclude = { "" })
@EqualsAndHashCode(exclude = {"cidadeOrigem", "cidadeDestino", "paradas", "horarioSaida", "horarioChegada"})
@Entity
public class Rota implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ManyToOne
	@JoinColumn(name = "cidadeOrigem_id")
	private Cidade cidadeOrigem;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ManyToOne
	@JoinColumn(name = "cidadeDestino_id")
	private Cidade cidadeDestino;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "ROTA_PARADA", joinColumns = @JoinColumn(name = "rota_id"), inverseJoinColumns = @JoinColumn(name = "parada_id"))
	private List<Parada> paradas;

	private Date horarioSaida;

	private Date horarioChegada;

	public Rota() {}

	public Rota(Long id,Cidade cidadeOrigem, Cidade cidadeDestino, List<Parada> paradas) {
		super();
		this.id = id;
		this.cidadeOrigem = cidadeOrigem;
		this.cidadeDestino = cidadeDestino;
		this.paradas = paradas;
	}
}
