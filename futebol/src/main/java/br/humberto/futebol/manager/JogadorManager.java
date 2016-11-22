/**
 * 
 */
package br.humberto.futebol.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.humberto.futebol.model.Jogador;
import br.humberto.futebol.model.Jogo;
import br.humberto.futebol.model.JogoBO;
import br.humberto.futebol.model.Time;
import br.humberto.futebol.model.TimeBO;
import br.humberto.futebol.util.DateUtil;

/**
 * @author Humberto Costa de Santana
 *
 */
@Stateless
public class JogadorManager {
	@PersistenceContext(unitName="futebolPU")
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public List<Jogador> todosOsJogadores()
	{
		List<Jogador> jogadores = getEm().createQuery("select j from Jogador j ").getResultList();
		return jogadores;
	}
	
	@SuppressWarnings("unchecked")
	public List<Jogo> listaDeSorteios(Date data)
	{
		Query query = getEm().createQuery("select j from Jogo j "
				+ " where j.data between :dataInicial and :dataFinal "
				+ " order by j.data ");
		
		query.setParameter("dataInicial", DateUtil.getStartOfDay(data));
		query.setParameter("dataFinal", DateUtil.getEndOfDay(data));
		
		List<Jogo> jogos = query.getResultList();
		
		return jogos;
	}
	
	public JogoBO recuperarJogo(Integer idJogo)
	{
		Query query = getEm().createQuery("select j from Jogo j "+
				 " where j.id=:id ");
		
		query.setParameter("id", idJogo);
		
		Jogo jogo = (Jogo) query.getSingleResult();
		
		JogoBO jogoBO = new JogoBO();
		jogoBO.setData(jogo.getLabel());
		jogoBO.setTimes(new ArrayList<TimeBO>());
		
		for (Time time: jogo.getTimes())
		{
			TimeBO timeBO = new TimeBO();
			jogoBO.getTimes().add(timeBO);
			
			timeBO.setNome(time.getNome());
			timeBO.setListaDeJogadores(time.getListaDeJogadores().stream().map(e -> e.getNome()).collect(Collectors.toList()));			
		}
		
		return jogoBO;
	}
	
	public Jogo sortearJogadores(List<Jogador> jogadores)
	{
		
		
		Jogo jogo = new Jogo();
		jogo.setData(new Date());
		jogo.setJogadoresConfirmados(jogadores);
		
		getEm().persist(jogo);
		
		int qtdJogadores = jogadores.size();
		int qtdExata = qtdJogadores/5;
		int resto = qtdJogadores%5;
		
		int qtdTimes = qtdExata + (resto==0?0:1);
		List<Time> times = new ArrayList<>(qtdTimes);
		for (int i=0;i<qtdTimes;i++)
		{
			Time time = new Time();
			time.setJogo(jogo);
			time.setNome("Time "+(i+1));
			time.setListaDeJogadores(new ArrayList<Jogador>());
						
			times.add(time);
		}
		
		Random sorteador = new Random(System.currentTimeMillis());
		
		Map<Integer, List<Jogador>> mapJogadores = jogadores.stream().collect(Collectors.groupingBy(Jogador::getQualidade));
		
		for (int i=0, qualidade=1;i<qtdJogadores;i++)
		{
			
			int timeAColocar = i%qtdTimes;
			
			List<Jogador> listaNaoEscolhidos = mapJogadores.get(qualidade);
			if (listaNaoEscolhidos.size()==1)
				qualidade++;
			
			int qtdNaoEscolhidos = listaNaoEscolhidos.size();
			int posicaoJogadorEscolhido = sorteador.nextInt(qtdNaoEscolhidos);			
			Jogador jogador = listaNaoEscolhidos.get(posicaoJogadorEscolhido);
			listaNaoEscolhidos.remove(posicaoJogadorEscolhido);
			
			Time time = times.get(timeAColocar); 
			time.getListaDeJogadores().add(jogador);
			getEm().persist(time);
		}
		
		jogo.setTimes(times);
		getEm().persist(jogo);
		
		return jogo;
	}
}
