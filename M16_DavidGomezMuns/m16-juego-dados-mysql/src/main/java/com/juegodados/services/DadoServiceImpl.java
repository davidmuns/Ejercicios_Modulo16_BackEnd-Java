package com.juegodados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juegodados.entities.Dado;
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
	public void eliminarAllTiradasJugador(List<Dado> dados) {
		iDadoRepository.deleteInBatch(dados);
	}

	@Override
	public void guardarNuevoDado(Dado dado) {
		iDadoRepository.save(dado);
	}

}
