package com.juegodados.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "juego")
public class Juego implements Serializable {

	@Id
	@Column(name = "id_juego")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idJuego;

	@Column(name = "id_jugador")
	private int idJugador;

	@Column(name = "id_dado")
	private int IdDado;

	@Column(name = "resultado")
	private boolean resultado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_jugador", insertable = false, updatable = false)
	private Jugador jugador;

	@OneToOne // (cascade = CascadeType.PERSIST, mappedBy = "juego")
	@JoinColumn(name = "id_dado", insertable = false, updatable = false)
	private Dado dado;

	public Juego() {

	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public Dado getDado() {
		return dado;
	}

	public void setDado(Dado dado) {
		this.dado = dado;
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

}
