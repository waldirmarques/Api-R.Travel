package br.com.Rtravel.domaim;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.Rtravel.enums.Perfil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data()
@ToString(exclude = { "" })
@EqualsAndHashCode(exclude = {"name", "email", "senha"})
@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	@Column(unique=true)
	private String email;

	@JsonIgnore  //Do not show password when retrieving data
	private String senha;
	
	@ElementCollection(fetch=FetchType.EAGER) //Sempre que buscar um usuário também pega seu perfil
	@CollectionTable(name="PERFIS") //Nome da tabela de ligação entre usuario e perfil
	private Set<Integer> perfis = new HashSet<>();
		
	
	public Usuario() {
		addPerfil(Perfil.ADMIN);
	}
	
	public Usuario(Integer id, String nome, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		addPerfil(Perfil.ADMIN);
	}
	
	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCod());
	}

	
}