package br.com.Rtravel.dto;

import java.io.Serializable;
import java.util.List;

import br.com.Rtravel.domaim.Cidade;
import br.com.Rtravel.domaim.Parada;
import br.com.Rtravel.domaim.Rota;

public class RotaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private List<Cidade> cidades;
	private List<Parada> paradas;
	
	public RotaDTO() {}
	
	public RotaDTO(Rota rota) {
		this.id = rota.getId();
		this.cidades = rota.getCidades();
		this.paradas = rota.getParadas();
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

	@Override
	public String toString() {
		return "RotaDTO{" +
				"id=" + id +
				", cidades=" + cidades +
				", parada=" + paradas +
				'}';
	}
}
