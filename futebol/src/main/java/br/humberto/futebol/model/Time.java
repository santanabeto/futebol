/**
 * 
 */
package br.humberto.futebol.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * @author Humberto Costa de Santana
 *
 */
@Entity
public class Time {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_time")
    @SequenceGenerator(allocationSize = 1, name="seq_time", sequenceName = "seq_time")
	private Integer id;
	private String nome;
	@ManyToOne
	@JoinColumn(name="id_jogo")
	private Jogo jogo;
	@ManyToMany
    @JoinTable(name="time_jogador", joinColumns={@JoinColumn(name="id_time")}, inverseJoinColumns={@JoinColumn(name="id_jogador")})
	private List<Jogador> listaDeJogadores;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Jogador> getListaDeJogadores() {
		return listaDeJogadores;
	}
	public void setListaDeJogadores(List<Jogador> listaDeJogadores) {
		this.listaDeJogadores = listaDeJogadores;
	}
	public Jogo getJogo() {
		return jogo;
	}
	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
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
		Time other = (Time) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
