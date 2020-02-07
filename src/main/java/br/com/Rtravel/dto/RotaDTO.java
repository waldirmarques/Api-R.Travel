package br.com.Rtravel.dto;

import java.io.Serializable;
import java.util.List;

import br.com.Rtravel.domaim.Cidade;
import br.com.Rtravel.domaim.Parada;

public class RotaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Cidade cidadeOrigem;
	private Cidade cidadeDestino;
	private List<Parada> parada;
	
	public RotaDTO() {}
	
	public RotaDTO(Long id, Cidade cidadeOrigem, Cidade cidadeDestino, List<Parada> parada) {
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
	
	
}
