package edu.fiuba.algoChess.interfaz.vista;

import edu.fiuba.algoChess.entidades.Pieza;
import edu.fiuba.algoChess.entorno.Tablero;
import edu.fiuba.algoChess.interfaz.controlladores.MoverAbajoController;
import edu.fiuba.algoChess.interfaz.controlladores.MoverArribaController;
import edu.fiuba.algoChess.interfaz.controlladores.MoverDerechaController;
import edu.fiuba.algoChess.interfaz.controlladores.MoverIzquierdaController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class MenuMovimiento {

	@Getter
	@Setter
	Pieza pieza;

	@Getter
	@Setter
	Tablero tablero;


	@Getter
	@Setter
	ImageView imageView;

	@Getter
	@Setter
	MapView mapView;

	@Getter
	@Setter
	int x;

	@Getter
	@Setter
	int y;

	MenuMovimiento(Pieza pieza, Tablero tablero, ImageView imagenPieza , MapView map){
		this.pieza = pieza;
		this.tablero = tablero;
		this.imageView = imagenPieza;
		this.mapView = map;
	}

	public void menuPopUp(){

		Stage stage = new Stage();
		VBox vbox = new VBox();
		this.x = pieza.getX();
		this.y = pieza.getY();

		Button arriba = new Button("Mover arriba");
		arriba.setStyle("-fx-background-color:#F1C40F;");
		arriba.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				moverArriba();
				//cambioTurno(head,namePiece);
				stage.close();
			}
		});

		Button abajo = new Button("Mover abajo");
		abajo.setStyle("-fx-background-color:#F1C40F;");
		abajo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				moverAbajo();
				//cambioTurno(head,namePiece);
				stage.close();
			}
		});

		Button izquierda = new Button("Mover izquierda");
		izquierda.setStyle("-fx-background-color:#F1C40F;");
		izquierda.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				moverIzquierda();
				//cambioTurno(head,namePiece);
				stage.close();
			}
		});

		Button derecha = new Button("Mover derecha");
		derecha.setStyle("-fx-background-color:#F1C40F;");
		derecha.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				moverDerecha();
				//cambioTurno(head,namePiece);
				stage.close();
			}
		});

		vbox.getChildren().addAll(arriba,abajo,izquierda,derecha);

		Scene theScene = new Scene(vbox);
		stage.setScene(theScene);
		stage.show();

	}

	public void moverArriba(){
		MoverArribaController moverArribaController = new MoverArribaController(pieza, tablero, this.getMapView(), this.getImageView());
		moverArribaController.moverArriba();
		sombrearPiezaAlPasarMouse(this.getImageView());
		//this.getMapView().addViewOnMap(this.getImageView(), pieza.getX(), pieza.getY()-1);

	}

	public void moverAbajo(){
		MoverAbajoController moverAbajoController = new MoverAbajoController(pieza, tablero, this.getMapView(), this.getImageView());
		moverAbajoController.moverAbajo();
		sombrearPiezaAlPasarMouse(this.getImageView());
		//this.getMapView().addViewOnMap(this.getImageView(), pieza.getX(), pieza.getY()+1);

	}

	public void moverIzquierda(){
		MoverIzquierdaController moverIzquierdaController = new MoverIzquierdaController(pieza, tablero, this.getMapView(), this.getImageView());
		moverIzquierdaController.moverIzquierda();
		sombrearPiezaAlPasarMouse(this.getImageView());
		//this.getMapView().addViewOnMap(this.getImageView(), pieza.getX()-1, pieza.getY());

	}

	public void moverDerecha(){
		MoverDerechaController moverDerechaController = new MoverDerechaController(pieza, tablero, this.getMapView(), this.getImageView());
		moverDerechaController.moverDerecha();
		sombrearPiezaAlPasarMouse(this.getImageView());
		//this.getMapView().addViewOnMap(this.getImageView(), pieza.getX()+1, pieza.getY());

	}

	public static void sombrearPiezaAlPasarMouse(ImageView imagenPieza){
		DropShadow rollOverColor = new DropShadow();
		imagenPieza.addEventHandler(MouseEvent.MOUSE_ENTERED,
				(event) -> imagenPieza.setEffect(rollOverColor));
		imagenPieza.addEventHandler(MouseEvent.MOUSE_EXITED,
				(event) -> imagenPieza.setEffect(null));
	}
}
