package edu.fiuba.algoChess.modelo.batallones;

import edu.fiuba.algoChess.interfaz.vista.MenuMovimiento;
import edu.fiuba.algoChess.modelo.entidades.Soldado;
import edu.fiuba.algoChess.modelo.entorno.Tablero;
import edu.fiuba.algoChess.modelo.entorno.Ubicacion;
import edu.fiuba.algoChess.modelo.excepciones.BatallonEnMovimientoException;
import edu.fiuba.algoChess.modelo.excepciones.CeldaYaOcupadaException;
import edu.fiuba.algoChess.modelo.entidades.Pieza;
import lombok.*;

import java.util.*;

@NoArgsConstructor
public class BatallonUtil extends Batallon {

	boolean enMovimiento = false;

	public BatallonUtil(Pieza pieza1, Pieza pieza2, Pieza pieza3 ){
		this.pieza1 = pieza1;
		this.pieza2 = pieza2;
		this.pieza3 = pieza3;
	}

	@Override
	public void moverseALaDerecha(Tablero campoDeBatalla) {
		Ubicacion ubicacionDerecha1 = getPieza1().getUbicacion().getUbicacionDerecha();
		Ubicacion ubicacionDerecha2 = getPieza2().getUbicacion().getUbicacionDerecha();
		Ubicacion ubicacionDerecha3 = getPieza3().getUbicacion().getUbicacionDerecha();
		this.moverBatallon(campoDeBatalla, ubicacionDerecha1, ubicacionDerecha2, ubicacionDerecha3);
	}

	@Override
	public void moverseALaIzquierda(Tablero campoDeBatalla) {
		Ubicacion ubicacionIzquierda1 = getPieza1().getUbicacion().getUbicacionIzquierda();
		Ubicacion ubicacionIzquierda2 = getPieza2().getUbicacion().getUbicacionIzquierda();
		Ubicacion ubicacionIzquierda3 = getPieza3().getUbicacion().getUbicacionIzquierda();
		this.moverBatallon(campoDeBatalla, ubicacionIzquierda1, ubicacionIzquierda2, ubicacionIzquierda3);
	}

	public void moverseArriba(Tablero campoDeBatalla) {
		Ubicacion ubicacionArriba1 = getPieza1().getUbicacion().getUbicacionArriba();
		Ubicacion ubicacionArriba2 = getPieza2().getUbicacion().getUbicacionArriba();
		Ubicacion ubicacionArriba3 = getPieza3().getUbicacion().getUbicacionArriba();
		this.moverBatallon(campoDeBatalla, ubicacionArriba1, ubicacionArriba2, ubicacionArriba3);
	}

	public void moverseAbajo(Tablero campoDeBatalla) {
		Ubicacion ubicacionAbajo1 = getPieza1().getUbicacion().getUbicacionAbajo();
		Ubicacion ubicacionAbajo2 = getPieza2().getUbicacion().getUbicacionAbajo();
		Ubicacion ubicacionAbajo3 = getPieza3().getUbicacion().getUbicacionAbajo();
		this.moverBatallon(campoDeBatalla, ubicacionAbajo1, ubicacionAbajo2, ubicacionAbajo3);
	}

	@Override
	public Batallon moverBatallon(Tablero campoDeBatalla, Ubicacion ubicacion1, Ubicacion ubicacion2, Ubicacion ubicacion3) {

		if (enMovimiento) {
			throw new BatallonEnMovimientoException("Batallon en movimiento");
		}

		Ubicacion ubicacionVieja1 = getPieza1().getUbicacion();
		Ubicacion ubicacionVieja2 = getPieza2().getUbicacion();
		Ubicacion ubicacionVieja3 = getPieza3().getUbicacion();

		enMovimiento = true;

		try {
			campoDeBatalla.eliminar(ubicacionVieja1);
			campoDeBatalla.eliminar(ubicacionVieja2);
			campoDeBatalla.eliminar(ubicacionVieja3);

			boolean sePuedenMoverTodos = true;
			try {
				this.getPieza1().moverPiezaDeBatallon(campoDeBatalla, ubicacion1);
			} catch (CeldaYaOcupadaException ex) {
				campoDeBatalla.ubicarEnCeldaFaseJuego(this.getPieza1(), ubicacionVieja1);
				sePuedenMoverTodos = false;
			}
			try {
				this.getPieza2().moverPiezaDeBatallon(campoDeBatalla, ubicacion2);
			} catch (CeldaYaOcupadaException ex) {
				try {
					campoDeBatalla.ubicarEnCeldaFaseJuego(this.getPieza2(), ubicacionVieja2);
					sePuedenMoverTodos = false;
				} catch (CeldaYaOcupadaException e) {
					campoDeBatalla.eliminar(pieza1.getUbicacion());
					campoDeBatalla.ubicarEnCeldaFaseJuego(pieza1, ubicacionVieja1);
					campoDeBatalla.ubicarEnCeldaFaseJuego(pieza2, ubicacionVieja2);
					throw new CeldaYaOcupadaException("Celda ya ocupada");
				}
			}
			try {
				this.getPieza3().moverPiezaDeBatallon(campoDeBatalla, ubicacion3);
			} catch (CeldaYaOcupadaException ex) {
				try {
					campoDeBatalla.ubicarEnCeldaFaseJuego(this.getPieza3(), ubicacionVieja3);
					sePuedenMoverTodos = false;
				} catch (CeldaYaOcupadaException e) {
					campoDeBatalla.eliminar(pieza1.getUbicacion());
					campoDeBatalla.eliminar(pieza2.getUbicacion());
					campoDeBatalla.ubicarEnCeldaFaseJuego(pieza1, ubicacionVieja1);
					campoDeBatalla.ubicarEnCeldaFaseJuego(pieza2, ubicacionVieja2);
					campoDeBatalla.ubicarEnCeldaFaseJuego(pieza3, ubicacionVieja3);
					throw new CeldaYaOcupadaException("Celda ya ocupada");
				}
			}

			this.getPieza1().actualizaRango(campoDeBatalla);
			this.getPieza2().actualizaRango(campoDeBatalla);
			this.getPieza3().actualizaRango(campoDeBatalla);

			if (!sePuedenMoverTodos) {
				return this.desagrupar();
			}

			return this;

		} finally {
			enMovimiento = false;

		}
	}

	public Batallon desagrupar(){
		this.pieza1.setBatallonActual(new BatallonNull());
		this.pieza2.setBatallonActual(new BatallonNull());
		this.pieza3.setBatallonActual(new BatallonNull());
		return new BatallonNull();
	}

	public static ArrayList<Pieza> armarPosibleBatallon(Pieza soldado) {
		return _armarPosibleBatallon(soldado,0);
	}

	private static ArrayList<Pieza> _armarPosibleBatallon(Pieza soldado, int control) {
		ArrayList<Pieza> soldadosBatallon = new ArrayList<>();
		int corte = 0;
		Pieza soldado1 = soldado;
		soldadosBatallon = soldado1.getSoldadosContiguos();
		while (soldadosBatallon.size() != 0 && corte < 10) {
			if (soldadosBatallon.size() >= 2) {
				soldadosBatallon.add(0, soldado1);
				return soldadosBatallon;
			}
			if (soldadosBatallon.size() == 1 && control == 0) {
				return _armarPosibleBatallon(soldadosBatallon.get(0),1);
			}
			corte++;
		}
		return soldadosBatallon;
	}

}

