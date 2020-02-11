package br.com.Rtravel.dto;

import java.io.Serializable;
import java.util.List;

import br.com.Rtravel.domaim.Cidade;
import br.com.Rtravel.domaim.Parada;
import br.com.Rtravel.domaim.Rota;

public class RotaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Cidade cidadeOrigem;
	private Cidade cidadeDestino;
	private List<Parada> paradas;
	
	public RotaDTO() {}
	
	public RotaDTO(Rota rota) {
		this.id = rota.getId();
		this.cidadeOrigem = rota.getCidadeOrigem();
		this.cidadeDestino = rota.getCidadeDestino();
		this.paradas = rota.getParadas();
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
				", cidadeOrigem=" + cidadeOrigem +
				", cidadeDestino=" + cidadeDestino +
				", paradas=" + paradas +
				'}';
	}
}
