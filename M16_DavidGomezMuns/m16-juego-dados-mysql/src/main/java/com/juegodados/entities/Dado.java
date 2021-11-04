package com.juegodados.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dado")
public class Dado implements Serializable {

	@Id
	@Column(name = "id_dado")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDado;

	@Column(name = "id_jugador")
	private int idJugador;

	@Column(name = "dado1")
	private Integer dado1;

	@Column(name = "dado2")
	private Integer dado2;

	@Column(name = "resultado")
	private Integer resultado;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_jugador", insertable = false, updatable = false)
	private Jugador jugador;

	public Dado() {

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

}
