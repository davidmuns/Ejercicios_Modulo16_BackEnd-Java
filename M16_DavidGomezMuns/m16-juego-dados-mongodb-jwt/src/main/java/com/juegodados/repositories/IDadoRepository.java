package com.juegodados.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.juegodados.collections.Dado;

public interface IDadoRepository extends MongoRepository<Dado, Integer> {

	List<Dado> findAllByIdJugador(int idJugador);

	void deleteAllByIdJugador(int idJugador);
}
