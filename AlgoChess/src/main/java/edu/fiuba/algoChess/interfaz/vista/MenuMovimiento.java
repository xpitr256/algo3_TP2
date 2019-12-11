package edu.fiuba.algoChess.interfaz.vista;

import edu.fiuba.algoChess.Modelo.entidades.Pieza;
import edu.fiuba.algoChess.Modelo.entorno.Tablero;
import edu.fiuba.algoChess.interfaz.controlladores.*;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

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
	String nombrePieza;

	PieceView pieceView;
	Stage stagePrincipal;
	Stage stageUbicar;
	PantallaPrincipal pantallaPrincipal;
	HBox head;


	MenuMovimiento(Pieza pieza, Tablero tablero, ImageView imagenPieza , MapView map,
				   PieceView pieceView){
		this.pieza = pieza;
		this.tablero = tablero;
		this.imageView = imagenPieza;
		this.mapView = map;
		this.pieceView = pieceView;
	}

	public void menuPopUp(){

		Stage stage = new Stage();
		VBox vbox = new VBox();

		Button arriba = new Button("Mover arriba");
		arriba.setStyle("-fx-background-color:#F1C40F;");
		arriba.setOnAction(e -> {
			moverArriba();
			stage.close();
		});

		Button abajo = new Button("Mover abajo");
		abajo.setStyle("-fx-background-color:#F1C40F;");
		abajo.setOnAction(e -> {
			moverAbajo();
			stage.close();
		});

		Button izquierda = new Button("Mover izquierda");
		izquierda.setStyle("-fx-background-color:#F1C40F;");
		izquierda.setOnAction(e -> {
			moverIzquierda();
			stage.close();
		});

		Button derecha = new Button("Mover derecha");
		derecha.setStyle("-fx-background-color:#F1C40F;");
		derecha.setOnAction(e -> {
			moverDerecha();
			stage.close();
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
	}

	public void moverAbajo(){
		MoverAbajoController moverAbajoController = new MoverAbajoController(pieza, tablero, this.getMapView(), this.getImageView());
		moverAbajoController.moverAbajo();
		sombrearPiezaAlPasarMouse(this.getImageView());
	}

	public void moverIzquierda(){
		MoverIzquierdaController moverIzquierdaController = new MoverIzquierdaController(pieza, tablero, this.getMapView(), this.getImageView());
		moverIzquierdaController.moverIzquierda();
		sombrearPiezaAlPasarMouse(this.getImageView());
	}

	public void moverDerecha(){
		MoverDerechaController moverDerechaController = new MoverDerechaController(pieza, tablero, this.getMapView(), this.getImageView());
		moverDerechaController.moverDerecha();
		sombrearPiezaAlPasarMouse(this.getImageView());
	}

	public static void sombrearPiezaAlPasarMouse(ImageView imagenPieza){
		DropShadow rollOverColor = new DropShadow();
		imagenPieza.addEventHandler(MouseEvent.MOUSE_ENTERED,
				(event) -> imagenPieza.setEffect(rollOverColor));
		imagenPieza.addEventHandler(MouseEvent.MOUSE_EXITED,
				(event) -> imagenPieza.setEffect(null));
	}

	public void alerta1seg(String Titulo, String Texto){

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle(Titulo);
		alert.setHeaderText(Texto);

		Thread thread = new Thread(() -> {
			try {

				Thread.sleep(1000);
				if (alert.isShowing()) {
					Platform.runLater(() -> alert.close());
				}
			} catch (Exception exp) {
				exp.printStackTrace();
			}
		});
		thread.setDaemon(true);
		thread.start();
		Optional<ButtonType> result = alert.showAndWait();
	}
}

