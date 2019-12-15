package edu.fiuba.algoChess.modelo.juego;

import edu.fiuba.algoChess.modelo.bandos.Bando;
import edu.fiuba.algoChess.modelo.bandos.BandoJugador1;
import edu.fiuba.algoChess.modelo.bandos.BandoJugador2;
import edu.fiuba.algoChess.modelo.entidades.Pieza;
import edu.fiuba.algoChess.modelo.entidades.Soldado;
import edu.fiuba.algoChess.modelo.entidades.*;
import edu.fiuba.algoChess.modelo.entorno.ObservadorTablero;
import edu.fiuba.algoChess.modelo.entorno.Tablero;
import edu.fiuba.algoChess.modelo.entorno.Ubicacion;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Juego {

	@Getter
	@Setter
	private Tablero tablero;

	@Getter
	@Setter
	private Jugador jugador1;

	@Getter
	@Setter
	private Jugador jugador2;

	@Getter
	@Setter
	private boolean activoBando1;

	@Getter
	@Setter
	private Bando bandoActivo;

	@Getter
	@Setter
	private Jugador jugadorActivo;

	@Getter
	@Setter
	private ArrayList<Pieza> piezasEnTablero;

	@Getter
	@Setter
	Boolean segundaEtapa = false;

	@Getter
	@Setter
	Boolean finDeJuego = false;

	@Getter
	@Setter
	Pieza receptor;

	private ObservadorTablero observadorTablero;

	public static final Soldado soldado = new Soldado();

	public Juego(String nombreJugador1, String nombreJugador2) {
			this.tablero = new Tablero(new BandoJugador1(), new BandoJugador2());
			this.jugador1 = new Jugador(nombreJugador1, "jugador1");
			this.jugador2 = new Jugador(nombreJugador2, "jugador2");
			this.jugador1.setBando(new BandoJugador1());
			this.jugador2.setBando(new BandoJugador2());
			this.piezasEnTablero = new ArrayList<>();
			this.bandoActivo = this.jugador1.getBando();
			this.jugadorActivo = this.jugador1;
			this.activoBando1 = true;
		}

		public Pieza crearPieza (String nombre, Ubicacion ubicacion){

			Pieza pieza = new PiezaNull();

				if (nombre.contains("Soldado")) {
					pieza = new Soldado(ubicacion, this.bandoActivo, this.tablero);
					this.getPiezasEnTablero().add(pieza);
					this.jugadorActivo.adquirirPieza(pieza);
					return pieza;
				}

				if (nombre.contains("Catapulta")) {
					pieza = new Catapulta(ubicacion, this.bandoActivo, this.tablero);
					this.getPiezasEnTablero().add(pieza);
					this.jugadorActivo.adquirirPieza(pieza);
					return pieza;
				}

				if (nombre.contains("Jinete")) {
					pieza = new Jinete(ubicacion, this.bandoActivo, this.tablero);
					this.getPiezasEnTablero().add(pieza);
					this.jugadorActivo.adquirirPieza(pieza);
					return pieza;
				}

				if (nombre.contains("Curandero")) {
					pieza = new Curandero(ubicacion, this.bandoActivo, this.tablero);
					this.getPiezasEnTablero().add(pieza);
					this.jugadorActivo.adquirirPieza(pieza);
					return pieza;
				}

			return pieza;
		}

	public void atacar (Pieza atacante, Pieza atacado){
		atacante.atacar(atacado);
		if(! atacado.getVida().stateEstaVivo()) {
			this.tablero.eliminar(atacado.getUbicacion());
		}
	}

	public void curar (Curandero curador, Pieza curado){
		curador.curar(curado);
	}

	public Pieza obtenerPiezaAPartirDeUbicacion(Ubicacion ubicacionPiezaObjetivo) {
		Pieza retorno = new PiezaNull();
		return this.getPiezasEnTablero().stream().filter(pieza -> ubicacionPiezaObjetivo.equals(pieza.getUbicacion())).findFirst().orElse(retorno);
	}

	public void pasarTurno(){
		this.jugadorActivo = activoBando1 ? this.jugador2: this.jugador1;
		this.bandoActivo = jugadorActivo.getBando();
		this.activoBando1 = ! activoBando1;
	}

	public void comenzarSegundoStage() {
		setSegundaEtapa(true);
	}

	public void terminarJuego() {
		setFinDeJuego(true);
	}
}