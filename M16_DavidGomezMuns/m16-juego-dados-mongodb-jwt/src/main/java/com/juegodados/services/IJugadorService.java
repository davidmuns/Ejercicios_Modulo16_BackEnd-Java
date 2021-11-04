package com.juegodados.services;

import java.util.List;
import java.util.Optional;

import com.juegodados.collections.Jugador;

public interface IJugadorService {
	public List<Jugador> getAllJugadores();

	public void guardarJugador(Jugador jugador);

	public Optional<Jugador> buscarPorIdJugador(int idJugador);

	public void eliminarJugador(Jugador jugador);

}
