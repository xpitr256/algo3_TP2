package edu.fiuba.algoChess.entorno;

import edu.fiuba.algoChess.juego.Jugador;
import edu.fiuba.algoChess.bandos.Bando;
import edu.fiuba.algoChess.entidades.Pieza;
import edu.fiuba.algoChess.excepciones.NoExisteNingunCasilleroParaLaUbicacionDadaException;
import edu.fiuba.algoChess.excepciones.NoSePuedeUbicarPorqueEstaOcupadoException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@NoArgsConstructor
public class Tablero {

	@Getter
	@Setter
	private Map<Ubicacion, Celda> campoDeBatalla;

	@Getter
	@Setter
	private Jugador jugadorActivo;

	public Tablero(Bando bandoJugador1, Bando  bandoJugador2) {
		this.campoDeBatalla = new HashMap<>();
		this.inicializarTablero(bandoJugador1, bandoJugador2);
		//this.celdaActiva=this.campoDeBatalla.get(new Ubicacion(1,1));
	}

	public void inicializarTablero(Bando bandoJugador1, Bando  bandoJugador2) {

		for (int i = -6; i <= 26; i++) {
			for (int j = -6; j <= 26; j++) {
				this.getCampoDeBatalla().put(new Ubicacion(i, j), new CeldaNull());
			}
		}

		for (int i = 1; i <= 20; i++) {
			for (int j = 1; j <= 20; j++) {
				this.getCampoDeBatalla().put(new Ubicacion(i, j), new Celda());
			}
		}
		this.asignarSectores(bandoJugador1, bandoJugador2);
	}

	public void asignarSectores(Bando bandoJugador1,Bando bandoJugador2){
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 20; j++) {
				this.getCelda(new Ubicacion(i, j)).setSectorDelJugador(bandoJugador1);
			}
		}
		for (int i = 11; i <= 20; i++) {
			for (int j = 1; j <= 20; j++) {
				this.getCelda(new Ubicacion(i, j)).setSectorDelJugador(bandoJugador2);
			}
		}
	}

	public Celda getCelda(Ubicacion ubicacion) {
		if (!this.campoDeBatalla.containsKey(ubicacion)) {
			throw new NoExisteNingunCasilleroParaLaUbicacionDadaException("No existe una celda en esa ubicacion");
		}else {
			return this.campoDeBatalla.get(ubicacion);
		}
	}

	public Celda getCeldaONull(Ubicacion ubicacion) {
		if (!this.campoDeBatalla.containsKey(ubicacion)) {
			return new CeldaNull();
		}else {
			return this.campoDeBatalla.get(ubicacion);
		}
	}

	public void ubicarEnCelda(Pieza pieza, Ubicacion ubicacion) {

		if (!this.campoDeBatalla.containsKey(ubicacion)) {
			throw new NoExisteNingunCasilleroParaLaUbicacionDadaException("No existe una celda en esa ubicacion");
		}

		if (this.campoDeBatalla.get(ubicacion).getPiezaActual().notEqualsNull()) {
			throw new NoSePuedeUbicarPorqueEstaOcupadoException("no se puede ubicar pieza por estar el casillero ocupado");
		}

		try {
			this.campoDeBatalla.get(ubicacion).guardarComienzo(pieza);
			pieza.setUbicacion(ubicacion);
			//pieza.getRango().actualizaPiezasEnRango(pieza);
		} catch (NoSePuedeUbicarPorqueEstaOcupadoException e) {
			throw new NoSePuedeUbicarPorqueEstaOcupadoException("no se puede ubicar pieza por estar el casillero ocupado");
		}
	}

	public void eliminar(Ubicacion ubicacion) {

		this.getCelda(ubicacion).eliminar();
	}

}
