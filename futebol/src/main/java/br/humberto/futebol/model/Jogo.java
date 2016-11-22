/**
 * 
 */
package br.humberto.futebol.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Humberto Costa de Santana
 *
 */
@Entity
public class Jogo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_jogo")
    @SequenceGenerator(allocationSize = 1, name="seq_jogo", sequenceName = "seq_jogo")
	private Integer id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	@ManyToMany(mappedBy="jogosConfirmados")
	private List<Jogador> jogadoresConfirmados;
	@OneToMany(mappedBy="jogo")
	private List<Time> times;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public List<Jogador> getJogadoresConfirmados() {
		return jogadoresConfirmados;
	}
	public void setJogadoresConfirmados(List<Jogador> jogadoresConfirmados) {
		this.jogadoresConfirmados = jogadoresConfirmados;
	}
	public List<Time> getTimes() {
		return times;
	}
	public void setTimes(List<Time> times) {
		this.times = times;
	}
	
	public String getLabel()
	{
		return new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(getData());
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
		Jogo other = (Jogo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
