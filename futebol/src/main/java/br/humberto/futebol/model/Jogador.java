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
import javax.persistence.SequenceGenerator;

/**
 * @author Humberto Costa de Santana
 *
 */
@Entity
public class Jogador {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_jogador")
    @SequenceGenerator(allocationSize = 1, name="seq_jogador", sequenceName = "seq_jogador")
	private Integer id;
	private String nome;	
	private Integer qualidade;	
	@ManyToMany
    @JoinTable(name="confirmacao_jogadores", joinColumns={@JoinColumn(name="id_jogador")}, inverseJoinColumns={@JoinColumn(name="id_jogo")})
	private List<Jogo> jogosConfirmados;
	
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
	public Integer getQualidade() {
		return qualidade;
	}
	public void setQualidade(Integer qualidade) {
		this.qualidade = qualidade;
	}
	public List<Jogo> getJogosConfirmados() {
		return jogosConfirmados;
	}
	public void setJogosConfirmados(List<Jogo> jogosConfirmados) {
		this.jogosConfirmados = jogosConfirmados;
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
		Jogador other = (Jogador) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
