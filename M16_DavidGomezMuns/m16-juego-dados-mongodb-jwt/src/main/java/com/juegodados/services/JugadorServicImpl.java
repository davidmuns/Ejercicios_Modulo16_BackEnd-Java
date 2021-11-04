package com.juegodados.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juegodados.collections.Jugador;
import com.juegodados.repositories.IJugadorRepository;

@Service
public class JugadorServicImpl implements IJugadorService {

	@Autowired
	IJugadorRepository iJugadorRepository;

	public List<Jugador> getAllJugadores() {
		return iJugadorRepository.findAll();
	}

	public void guardarJugador(Jugador jugador) {
		iJugadorRepository.save(jugador);
	}

	public Optional<Jugador> buscarPorIdJugador(int idJugador) {
		return iJugadorRepository.findById(idJugador);
	}

	public void eliminarJugador(Jugador jugador) {
		iJugadorRepository.delete(jugador);
	}
}
