package com.juegodados.utilities;

import java.util.List;
import java.util.function.Predicate;

import com.juegodados.entities.Dado;
import com.juegodados.entities.Juego;
import com.juegodados.entities.Jugador;

public interface IUtilities {

	public boolean nombreJugadorExiste(List<Jugador> jugadores, Predicate<Jugador> predicate);

	public Jugador guardarJugador(Jugador nuevoJugador);

	public Double calcularRatioJugador(List<Juego> juegos);

	public double getPorcentajeTotalVictorias(List<Juego> juegos);

	public List<Jugador> getListaJugadoresOrdenadaPorRanking(List<Jugador> jugadores);

	public Dado getResultadoLanzamientoDados(int idJugador);

	public Juego getNuevoJuego(int idJugador, Dado dado);
}
