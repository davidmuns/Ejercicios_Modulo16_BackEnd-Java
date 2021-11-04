package com.juegodados.controllers;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.juegodados.collections.Dado;
import com.juegodados.collections.Juego;
import com.juegodados.collections.Jugador;
import com.juegodados.services.IDadoService;
import com.juegodados.services.IJuegoService;
import com.juegodados.services.IJugadorService;
import com.juegodados.utilities.IUtilities;

@RestController
public class Controlador {

	@Autowired
	private IJuegoService iJuegoService;

	@Autowired
	private IDadoService iDadoService;

	@Autowired
	private IJugadorService iJugadorService;

	@Autowired
	private IUtilities iUtilities;

	// OBTENER LISTADO DE TODOS LOS JUGADORES
	@GetMapping("jugadores")
	public ResponseEntity<?> getJugadores() {
		return ResponseEntity.ok(iJugadorService.getAllJugadores());
	}

	// CREAR NUEVO JUGADOR
	@PostMapping("jugadores")
	public ResponseEntity<?> crearJugador(@RequestBody Jugador nuevoJugador) {

		List<Jugador> jugadores = iJugadorService.getAllJugadores();

		boolean existe = iUtilities.nombreJugadorExiste(jugadores,
				p -> p.getNombre().equalsIgnoreCase(nuevoJugador.getNombre())
						&& !p.getNombre().equalsIgnoreCase("anonimo"));

		if (existe) {
			return ResponseEntity.badRequest().body("Ya existe un jugador llamado " + nuevoJugador.getNombre());
		}

		iJugadorService.guardarJugador(iUtilities.guardarJugador(nuevoJugador));

		return ResponseEntity.ok(nuevoJugador);
	}

	// MODIFICAR NOMBRE JUGADOR
	@PutMapping("jugadores/{idJugador}")
	public ResponseEntity<?> modificarNombreJugador(@PathVariable("idJugador") int idJugador,
			@RequestBody Jugador jugador) {

		if (jugador.getNombre() == null)
			jugador.setNombre("");

		List<Jugador> jugadores = iJugadorService.getAllJugadores();

		boolean existe = iUtilities.nombreJugadorExiste(jugadores,
				p -> p.getNombre().equalsIgnoreCase(jugador.getNombre()));

		Optional<Jugador> optionalJugador = iJugadorService.buscarPorIdJugador(idJugador);

		if (optionalJugador.isPresent() && existe == false && !jugador.getNombre().isBlank()) {

			Jugador actualizarJugador = optionalJugador.get();
			actualizarJugador.setNombre(jugador.getNombre());
			iJugadorService.guardarJugador(actualizarJugador);

			return ResponseEntity.ok(actualizarJugador);
		}

		if (optionalJugador.isEmpty())
			return ResponseEntity.status(404)
					.body("El jugador con id " + idJugador + " no existe. Consultar el listado de jugadores.");

		if (existe)
			return ResponseEntity.status(404).body(
					"Ya existe un jugador llamado " + jugador.getNombre() + ". Consultar el listado de jugadores.");

		if (jugador.getNombre().isBlank())
			return ResponseEntity.status(404).body("El campo nombre es obligatorio.");

		return null;
	}

	// ELIMINAR UN JUGADOR
	@DeleteMapping("jugadores/{idJugador}")
	public ResponseEntity<?> eliminarUnJugador(@PathVariable("idJugador") int idJugador) {

		Optional<Jugador> optionalJugador = iJugadorService.buscarPorIdJugador(idJugador);

		if (optionalJugador.isPresent()) {
			iJugadorService.eliminarJugador(optionalJugador.get());
			return ResponseEntity.ok("Eliminado jugador " + optionalJugador.get().getNombre() + " con id " + idJugador);
		}
		return ResponseEntity.status(404)
				.body("El jugador con id " + idJugador + " no existe. Consultar el listado de jugadores.");

	}

	// NUEVA JUGADA
	@PostMapping("jugadores/{idJugador}/juego")
	public ResponseEntity<?> jugar(@PathVariable("idJugador") int idJugador) {

		Optional<Jugador> optionalJugador = iJugadorService.buscarPorIdJugador(idJugador);

		if (optionalJugador.isPresent()) {

			Jugador jugador = optionalJugador.get();

			Dado nuevoDado = iUtilities.getResultadoLanzamientoDados(idJugador);

			Juego nuevoJuego = iUtilities.getNuevoJuego(idJugador, nuevoDado);

			nuevoDado.setIdJuego(nuevoJuego.getIdJuego());

			iDadoService.guardarNuevoDado(nuevoDado);

			iJuegoService.guardarNuevoJuego(nuevoJuego);

			List<Juego> juegos = iJuegoService.getAllJuegosByIdJugador(idJugador);

			List<Dado> dados = iDadoService.getAllTiradasByIdJugador(idJugador);

			jugador.setJuegos(juegos);

			jugador.setDados(dados);

			jugador.setRatio(iUtilities.calcularRatioJugador(juegos));

			iJugadorService.guardarJugador(jugador);

			return ResponseEntity.ok(nuevoDado);
		}

		return ResponseEntity.status(404)
				.body("El jugador con id " + idJugador + " no existe. Consultar el listado de jugadores.");

	}

	// JUGADAS POR JUGADOR
	@GetMapping("jugadores/{idJugador}/juego")
	public ResponseEntity<?> getJugadasUnJugador(@PathVariable("idJugador") int idJugador) {

		List<Juego> juegos = iJuegoService.getAllJuegosByIdJugador(idJugador);

		List<Dado> dados = iDadoService.getAllTiradasByIdJugador(idJugador);

		Optional<Jugador> optionalJugador = iJugadorService.buscarPorIdJugador(idJugador);

		if (optionalJugador.isPresent()) {

			Jugador jugador = optionalJugador.get();
			jugador.setJuegos(juegos);
			jugador.setDados(dados);

			return ResponseEntity.ok(jugador);

		}
		return ResponseEntity.status(404)
				.body("El jugador con id " + idJugador + " no existe. Consultar el listado de jugadores.");

	}

	// BORRAR TODAS LAS JUGADAS DE UN JUGADOR
	@DeleteMapping("jugadores/{idJugador}/juego")
	public ResponseEntity<?> eliminarJugadasUnJugador(@PathVariable("idJugador") int idJugador) {

		Optional<Jugador> optionalJugador = iJugadorService.buscarPorIdJugador(idJugador);

		if (optionalJugador.isPresent()) {
			Jugador jugador = optionalJugador.get();

			iJuegoService.eliminarAllJuegosByIdJugador(idJugador);

			iDadoService.eliminarAllTiradasByIdJugador(idJugador);

			jugador.setRatio(0.0);

			iJugadorService.guardarJugador(jugador);
			return ResponseEntity.ok(jugador);

		} else {
			return ResponseEntity.status(404)
					.body("El jugador con id " + idJugador + " no existe. Consultar el listado de jugadores.");

		}

	}

	// OBTENER PROCENTAJE TOTAL DE VICTORIAS
	@GetMapping("jugadores/ranking")
	public ResponseEntity<?> porcentajeVictorias() {

		List<Juego> juegos = iJuegoService.getAllJuegos();

		DecimalFormat df = new DecimalFormat("####.##");

		Double porcentajeTotalVictorias = iUtilities.getPorcentajeTotalVictorias(juegos);

		return ResponseEntity.ok("Total partidas jugadas: " + juegos.size() + "\nTotal victorias: "
				+ (int) ((juegos.size()) * (porcentajeTotalVictorias / 100)) + "\nPorcentaje victorias: "
				+ df.format(porcentajeTotalVictorias) + " %");

	}

	// OBTENER JUGADOR CON PEOR PORCENTAJE DE VICTORIAS
	@GetMapping("jugadores/ranking/peor")
	public ResponseEntity<?> peorJugador() {

		List<Jugador> jugadores = iJugadorService.getAllJugadores();

		Jugador peorJugador;

		if (jugadores.size() != 0) {
			peorJugador = iUtilities.getListaJugadoresOrdenadaPorRanking(jugadores).get(0);
			return ResponseEntity.ok(peorJugador);
		}
		return ResponseEntity.status(404).body("No hay jugadores en la lista.");

	}

	// OBTENER JUGADOR CON MEJOR PORCENTAJE DE VICTORIAS
	@GetMapping("jugadores/ranking/mejor")
	public ResponseEntity<?> mejorJugador() {

		List<Jugador> jugadores = iJugadorService.getAllJugadores();

		Jugador mejorJugador;

		if (jugadores.size() != 0) {
			mejorJugador = iUtilities.getListaJugadoresOrdenadaPorRanking(jugadores).get(jugadores.size() - 1);
			return ResponseEntity.ok(mejorJugador);
		}
		return ResponseEntity.status(404).body("No hay jugadores en la lista.");

	}

}
