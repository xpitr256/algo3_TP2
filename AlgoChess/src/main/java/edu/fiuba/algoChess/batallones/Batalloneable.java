package edu.fiuba.algoChess.batallones;

import edu.fiuba.algoChess.entidades.Movible;
import edu.fiuba.algoChess.entidades.Pieza;
import edu.fiuba.algoChess.rangos.Agrupable;
import edu.fiuba.algoChess.rangos.Rango;
import edu.fiuba.algoChess.entorno.Tablero;

import java.util.ArrayList;
import java.util.Collection;

public interface Batalloneable extends Movible, Agrupable {

	@Override
	Agrupable agrupar(Pieza pieza1, Pieza pieza2, Pieza pieza3);

	@Override
	Collection<Pieza> desagrupar(Agrupable grupo);

	@Override
	Rango actualizaRangoInmediato(Pieza piezaCentral, Tablero tablero);

	@Override
	void actualizaPiezasEnRango(Pieza piezaCentral);

	@Override
	ArrayList<Pieza> getPiezasEnRango();

	Batalloneable crearBatallon(Pieza pieza1, Pieza pieza2, Pieza pieza3);

	boolean equals(Batalloneable batallon);
	
	Pieza getPieza1();

	Pieza getPieza2();

	Pieza getPieza3();

	void setPieza1(Pieza pieza1);

	void setPieza2(Pieza pieza2);

	void setPieza3(Pieza pieza3);

}

