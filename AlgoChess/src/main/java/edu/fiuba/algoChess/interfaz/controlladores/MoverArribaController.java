package edu.fiuba.algoChess.interfaz.controlladores;

import edu.fiuba.algoChess.entidades.Pieza;
import edu.fiuba.algoChess.entorno.Tablero;
import edu.fiuba.algoChess.interfaz.vista.MapView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MoverArribaController {

	private Pieza pieza;
	private Tablero tablero;
	private MapView map;
	private ImageView imagenPieza;

	public MoverArribaController(Pieza pieza, Tablero tablero, MapView map, ImageView imagenPieza) {
		this.pieza = pieza;
		this.tablero = tablero;
		this.map = map;
		this.imagenPieza = imagenPieza;
	}

	public void moverArriba(){

		pieza.moverseAbajo(tablero);

		DropShadow rollOverColor = new DropShadow();
		this.imagenPieza.addEventHandler(MouseEvent.MOUSE_ENTERED,
				(event) -> this.imagenPieza.setEffect(rollOverColor));
		this.imagenPieza.addEventHandler(MouseEvent.MOUSE_EXITED,
				(event) -> this.imagenPieza.setEffect(null));

		this.map.addViewOnMap(this.imagenPieza, this.pieza.getUbicacion().getX(), this.pieza.getUbicacion().getY());

	}
}
