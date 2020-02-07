package br.com.Rtravel.domaim;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Rota implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	//@JsonIgnore
	//@ManyToOne
	//@JoinColumn(name="cidadeOrigem_id")
	private Cidade cidadeOrigem;
	
	//@JsonIgnore
	//@ManyToOne
	//@JoinColumn(name="cidadeDestino_id")
	private Cidade cidadeDestino;
	
	//@JsonIgnore //Cola-se essa anotação do lado que é para vir os produtos
	//@ManyToMany(mappedBy = "rotas")
	private List<Parada> parada = new ArrayList<>();
	
	public Rota() {}
	

	public Rota(Long id, Cidade cidadeOrigem, Cidade cidadeDestino, List<Parada> parada) {
		super();
		this.id = id;
		this.cidadeOrigem = cidadeOrigem;
		this.cidadeDestino = cidadeDestino;
		this.parada = parada;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cidade getCidadeOrigem() {
		return cidadeOrigem;
	}
	public void setCidadeOrigem(Cidade cidadeOrigem) {
		this.cidadeOrigem = cidadeOrigem;
	}
	public Cidade getCidadeDestino() {
		return cidadeDestino;
	}
	public void setCidadeDestino(Cidade cidadeDestino) {
		this.cidadeDestino = cidadeDestino;
	}
	public List<Parada> getParada() {
		return parada;
	}
	public void setParada(List<Parada> parada) {
		this.parada = parada;
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
	
}
