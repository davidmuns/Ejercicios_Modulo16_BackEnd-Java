package com.juegodados.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.juegodados.collections.Juego;

public interface IJuegoRepository extends MongoRepository<Juego, Integer> {

	List<Juego> findAllByIdJugador(int idJugador);

	void deleteAllByIdJugador(int idJugador);
}
