package com.juegodados.utilities;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.juegodados.collections.Dado;
import com.juegodados.collections.Juego;
import com.juegodados.collections.Jugador;

@Component
public class ControladorUtilitiesImpl implements IUtilities {

	@Override
	public boolean nombreJugadorExiste(List<Jugador> jugadores, Predicate<Jugador> predicate) {
		boolean existe = false;
		for (Jugador jugador : jugadores) {
			if (predicate.test(jugador))
				existe = true;
		}
		return existe;
	}

	@Override
	public Jugador guardarJugador(Jugador nuevoJugador) {

		if (nuevoJugador.getNombre() == null || nuevoJugador.getNombre().isBlank())
			nuevoJugador.setNombre("anonimo");

		nuevoJugador.setNombre(nuevoJugador.getNombre());

		return nuevoJugador;
	}

	public Dado getResultadoLanzamientoDados(int idJugador) {
		Dado dado = new Dado();

		int a = (int) (Math.random() * 6) + 1;
		int b = (int) (Math.random() * 6) + 1;

		dado.setIdJugador(idJugador);
		dado.setDado1(a);
		dado.setDado2(b);
		dado.setResultado(a + b);

		return dado;
	}

	public Juego getNuevoJuego(int idJugador, Dado dado) {
		Juego juego = new Juego();
		juego.setIdJugador(idJugador);
		juego.setIdDado(dado.getIdDado());
		juego.setResultado(dado.getResultado().equals(7) ? true : false);

		return juego;
	}

	public Double calcularRatioJugador(List<Juego> juegos) {
		double victorias = 0.0;
		double partidas = (double) juegos.size();
		for (Juego juego : juegos) {
			if (juego.getResultado() == true) {
				victorias++;
			}
		}

		return (victorias / partidas) * 100;
	}

	public double getPorcentajeTotalVictorias(List<Juego> juegos) {

		double victorias = 0.0;
		double porcentajeTotalVictorias = 0.0;
		double partidas = (double) juegos.size();

		for (Juego juego : juegos) {
			if (juego.getResultado() == true) {
				victorias++;
			}
		}

		if (partidas == 0.0)
			porcentajeTotalVictorias = 0.0;
		else
			porcentajeTotalVictorias = (victorias / partidas) * 100;

		return porcentajeTotalVictorias;

	}

	public List<Jugador> getListaJugadoresOrdenadaPorRanking(List<Jugador> jugadores) {
		Collections.sort(jugadores, (j1, j2) -> j1.getRatio().compareTo(j2.getRatio()));
		return jugadores;
	}

}
