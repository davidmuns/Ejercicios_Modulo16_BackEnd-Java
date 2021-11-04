package com.juegodados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juegodados.collections.Dado;
import com.juegodados.repositories.IDadoRepository;

@Service
public class DadoServiceImpl implements IDadoService {

	@Autowired
	IDadoRepository iDadoRepository;

	@Override
	public List<Dado> getAllTiradasByIdJugador(int idJugador) {
		return iDadoRepository.findAllByIdJugador(idJugador);
	}

	@Override
	public void eliminarAllTiradasByIdJugador(int idJugador) {
		iDadoRepository.deleteAllByIdJugador(idJugador);
	}

	@Override
	public void guardarNuevoDado(Dado dado) {
		iDadoRepository.save(dado);
	}

}
