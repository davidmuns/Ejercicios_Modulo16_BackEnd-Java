package com.juegodados.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dados")
public class Dado {

	@Id
	private int idDado;
	private int idJugador;
	private int idJuego;
	private Integer dado1;
	private Integer dado2;
	private Integer resultado;

	public Dado() {
		idDado = (int) (Math.random() * 1000000);
	}

	public int getIdDado() {
		return idDado;
	}

	public void setIdDado(int idDado) {
		this.idDado = idDado;
	}

	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public Integer getDado1() {
		return dado1;
	}

	public void setDado1(Integer dado1) {
		this.dado1 = dado1;
	}

	public Integer getDado2() {
		return dado2;
	}

	public void setDado2(Integer dado2) {
		this.dado2 = dado2;
	}

	public Integer getResultado() {
		return resultado;
	}

	public void setResultado(Integer resultado) {
		this.resultado = resultado;
	}

	public int getIdJuego() {
		return idJuego;
	}

	public void setIdJuego(int idJuego) {
		this.idJuego = idJuego;
	}

	@Override
	public String toString() {
		return "Dado [idDado=" + idDado + ", idJugador=" + idJugador + ", idJuego=" + idJuego + ", dado1=" + dado1
				+ ", dado2=" + dado2 + ", resultado=" + resultado + "]";
	}

}
