package com.juegodados.services;

import java.util.List;

import com.juegodados.entities.Dado;

public interface IDadoService {
	public List<Dado> getAllTiradasByIdJugador(int idJugador);

	public void eliminarAllTiradasJugador(List<Dado> dados);

	public void guardarNuevoDado(Dado dado);

}
