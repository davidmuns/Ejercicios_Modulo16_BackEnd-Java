package com.juegodados.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.juegodados.collections.Jugador;

public interface IJugadorRepository extends MongoRepository<Jugador, Integer> {

}
