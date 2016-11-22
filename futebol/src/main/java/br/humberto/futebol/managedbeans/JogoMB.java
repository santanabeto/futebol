/**
 * 
 */
package br.humberto.futebol.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.mobile.event.SwipeEvent;

import br.humberto.futebol.manager.JogadorManager;
import br.humberto.futebol.model.Jogador;
import br.humberto.futebol.model.Jogo;

/**
 * @author Humberto Costa de Santana
 *
 */
@ManagedBean
@ViewScoped
public class JogoMB {
	private List<Jogador> jogadores;
	private Jogo jogo;
	
	@EJB private JogadorManager jogadorManager;
	
	@PostConstruct
	public void init()
	{
		setJogadores(jogadorManager.todosOsJogadores());
	}
	
	public void removerJogador(SwipeEvent event) {
        Jogador jogador = (Jogador) event.getData();
        jogadores.remove(jogador);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
        		FacesMessage.SEVERITY_INFO, "Jogador removido", jogador.getNome()));
    }
	
	public String salvarParticipantes()
	{
		setJogo(jogadorManager.sortearJogadores(jogadores)); 
		return "pm:sorteio";
	}
	
	public List<Jogador> getJogadores() {
		return jogadores;
	}
	public void setJogadores(List<Jogador> jogadores) {
		this.jogadores = jogadores;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
}
