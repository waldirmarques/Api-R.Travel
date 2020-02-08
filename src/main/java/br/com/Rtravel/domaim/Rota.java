package br.com.Rtravel.domaim;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Rota implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToMany
	@JoinTable(name = "ROTA_CIDADE", joinColumns = @JoinColumn(name = "rota_id"), inverseJoinColumns = @JoinColumn(name = "cidade_id"))
	private List<Cidade> cidades;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "ROTA_PARADA", joinColumns = @JoinColumn(name = "rota_id"), inverseJoinColumns = @JoinColumn(name = "parada_id"))
	private List<Parada> paradas;

	private Date horarioSaida;

	private Date horarioChegadda;

	public Rota() {}
	

	public Rota(Long id,List<Cidade> cidades, List<Parada> paradas) {
		super();
		this.id = id;
		this.cidades = cidades;
		this.paradas = paradas;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Parada> getParadas() {
		return paradas;
	}
	public void setParadas(List<Parada> paradas) {
		this.paradas = paradas;
	}

	public Date getHorarioSaida() {
		return horarioSaida;
	}

	public void setHorarioSaida(Date horarioSaida) {
		this.horarioSaida = horarioSaida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rota other = (Rota) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rota{" +
				"id=" + id +
				", cidades=" + cidades +
				", paradas=" + paradas +
				", horarioSaida=" + horarioSaida +
				", horarioChegadda=" + horarioChegadda +
				'}';
	}
}
