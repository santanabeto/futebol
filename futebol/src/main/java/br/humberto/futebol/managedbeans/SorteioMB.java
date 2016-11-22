/**
 * 
 */
package br.humberto.futebol.managedbeans;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.humberto.futebol.manager.JogadorManager;
import br.humberto.futebol.model.Jogo;
import br.humberto.futebol.model.JogoBO;

/**
 * @author Humberto Costa de Santana
 *
 */
@ManagedBean
@ViewScoped
public class SorteioMB {
	private List<Jogo> listaDeSorteios;
	@EJB private JogadorManager jogadorManager;	
	private JogoBO jogoEscolhido;
	
	@PostConstruct
	public void init()
	{
		setListaDeSorteios(jogadorManager.listaDeSorteios(new Date()));
	}
	
	public String escolherJogo(Integer idJogo)
	{
		setJogoEscolhido(jogadorManager.recuperarJogo(idJogo));
		
		return "pm:sorteio";
	}

	public List<Jogo> getListaDeSorteios() {
		return listaDeSorteios;
	}

	public void setListaDeSorteios(List<Jogo> listaDeSorteios) {
		this.listaDeSorteios = listaDeSorteios;
	}

	public JogoBO getJogoEscolhido() {
		return jogoEscolhido;
	}

	public void setJogoEscolhido(JogoBO jogoEscolhido) {
		this.jogoEscolhido = jogoEscolhido;
	}

}
