package com.juegodados.collections;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "jugadores")
public class Jugador {

	@Id
	private int idJugador;

	@Field
	private String nombre;

	@Field
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date fecha;

	@Field
	private Double ratio = 0.0;

	@DBRef(db = "juegos")
	private List<Juego> juegos = new ArrayList<>();

	@DBRef(db = "dados")
	private List<Dado> dados = new ArrayList<>();

	public Jugador() {
		idJugador = (int) (Math.random() * 1000000);
		fecha = new Date();
	}

	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	public List<Juego> getJuegos() {
		return juegos;
	}

	public void setJuegos(List<Juego> juegos) {
		this.juegos = juegos;
	}

	public List<Dado> getDados() {
		return dados;
	}

	public void setDados(List<Dado> dados) {
		this.dados = dados;
	}

	@Override
	public String toString() {
		return "Jugador [idJugador=" + idJugador + ", nombre=" + nombre + ", fecha=" + fecha + ", ratio=" + ratio
				+ ", juegos=" + juegos + ", dados=" + dados + "]";
	}

}
