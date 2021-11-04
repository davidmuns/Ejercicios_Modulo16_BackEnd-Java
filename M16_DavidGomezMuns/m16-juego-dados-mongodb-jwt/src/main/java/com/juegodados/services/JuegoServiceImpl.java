package com.juegodados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juegodados.collections.Juego;
import com.juegodados.repositories.IJuegoRepository;

@Service
public class JuegoServiceImpl implements IJuegoService {

	@Autowired
	IJuegoRepository iJuegoRepository;

	@Override
	public List<Juego> getAllJuegosByIdJugador(int idJugador) {
		return iJuegoRepository.findAllByIdJugador(idJugador);
	}

	@Override
	public void eliminarAllJuegosByIdJugador(int idJugador) {
		iJuegoRepository.deleteAllByIdJugador(idJugador);
	}

	@Override
	public void guardarNuevoJuego(Juego nuevoJuego) {
		iJuegoRepository.save(nuevoJuego);
	}

	@Override
	public List<Juego> getAllJuegos() {
		return iJuegoRepository.findAll();
	}

}
