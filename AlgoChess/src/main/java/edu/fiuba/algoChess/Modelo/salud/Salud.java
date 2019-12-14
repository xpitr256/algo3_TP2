package edu.fiuba.algoChess.Modelo.salud;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public abstract class Salud implements Curable, Herible, StateVive {

	@Getter
	@Setter
	private double valorCompleto;

	@Getter
	@Setter
	private double valorActual;

	@Override
	public abstract Salud curar(double salud);

	public Salud morir(){
		this.valorActual = 0.0;
		return new SaludMuerto();
	}

	public Salud llenar(){

		return new SaludLlena(this.valorCompleto);
	}

	public void stateComportarse() {
	}

	// Herramienta para testing
	public boolean igualA(Salud salud) {
		return (this.getValorCompleto() == salud.getValorCompleto() && this.getValorActual() == salud.getValorActual());
 }

}
