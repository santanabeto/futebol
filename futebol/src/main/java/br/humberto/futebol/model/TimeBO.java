/**
 * 
 */
package br.humberto.futebol.model;

import java.util.List;

/**
 * @author Humberto Costa de Santana
 *
 */
public class TimeBO {
	private String nome;
	private List<String> listaDeJogadores;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<String> getListaDeJogadores() {
		return listaDeJogadores;
	}
	public void setListaDeJogadores(List<String> listaDeJogadores) {
		this.listaDeJogadores = listaDeJogadores;
	}
}
