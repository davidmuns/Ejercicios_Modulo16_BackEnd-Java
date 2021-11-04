package com.juegodados.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "juegos")
public class Juego {

	@Id
	private int idJuego;
	private int idJugador;
	private int IdDado;
	private boolean resultado;

	public Juego() {
		this.idJuego = (int) (Math.random() * 1000000);

	}

	public int getIdJuego() {
		return idJuego;
	}

	public void setIdJuego(int idJuego) {
		this.idJuego = idJuego;
	}

	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public int getIdDado() {
		return IdDado;
	}

	public void setIdDado(int idDado) {
		IdDado = idDado;
	}

	public boolean getResultado() {
		return resultado;
	}

	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}

	@Override
	public String toString() {
		return "Juego [idJuego=" + idJuego + ", idJugador=" + idJugador + ", IdDado=" + IdDado + ", resultado="
				+ resultado + "]";
	}

}
