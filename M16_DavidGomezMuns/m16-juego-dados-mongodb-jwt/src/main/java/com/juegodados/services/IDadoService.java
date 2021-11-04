package com.juegodados.services;

import java.util.List;

import com.juegodados.collections.Dado;

public interface IDadoService {
	public List<Dado> getAllTiradasByIdJugador(int idJugador);

	public void eliminarAllTiradasByIdJugador(int idJugador);

	public void guardarNuevoDado(Dado dado);

}
