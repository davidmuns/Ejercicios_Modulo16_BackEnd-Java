package com.juegodados.services;

import java.util.List;

import com.juegodados.collections.Juego;

public interface IJuegoService {
	public void guardarNuevoJuego(Juego nuevoJuego);

	public List<Juego> getAllJuegos();

	public List<Juego> getAllJuegosByIdJugador(int idJugador);

	public void eliminarAllJuegosByIdJugador(int idJugador);
}
