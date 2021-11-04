package com.juegodados.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juegodados.entities.Dado;

public interface IDadoRepository extends JpaRepository<Dado, Integer> {

	List<Dado> findAllByIdJugador(int idJugador);

	void deleteAllByIdJugador(int idJugador);
}
