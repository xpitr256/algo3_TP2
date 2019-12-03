package edu.fiuba.algoChess;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public interface Rango {

	public abstract Rango actualizaRangoInmediato(Pieza piezaCentral, Tablero tablero);
	public abstract void actualizaPiezasEnRango(Pieza piezaCentral);
	public abstract ArrayList<Pieza> getPiezasEnRango();


}