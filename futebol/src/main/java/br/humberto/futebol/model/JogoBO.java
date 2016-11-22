/**
 * 
 */
package br.humberto.futebol.model;

import java.util.List;

/**
 * @author Humberto Costa de Santana
 *
 */
public class JogoBO {
	private String data;
	private List<TimeBO> times;
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public List<TimeBO> getTimes() {
		return times;
	}
	public void setTimes(List<TimeBO> times) {
		this.times = times;
	}
}
