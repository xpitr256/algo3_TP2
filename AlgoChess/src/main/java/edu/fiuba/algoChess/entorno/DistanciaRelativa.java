package edu.fiuba.algoChess.entorno;

import edu.fiuba.algoChess.entidades.Pieza;

public class DistanciaRelativa {

    //protected int distancia;

    public DistanciaRelativa(//int distancia
     ){
       // this.distancia = distancia;
    }

    public static DistanciaRelativa getDistanciaRelativa (Pieza piezaEje, Pieza otraPieza){
        Ubicacion ubicacionOtraPieza = otraPieza.getUbicacion();
        Ubicacion ubicacionPiezaEje = piezaEje.getUbicacion();

        int otraPiezaX = ubicacionOtraPieza.getCoordenadaX();
        int otraPiezaY = ubicacionOtraPieza.getCoordenadaY();
        int piezaEjeX = ubicacionPiezaEje.getCoordenadaX();
        int piezaEjeY = ubicacionPiezaEje.getCoordenadaY();

        DistanciaRelativa distanciaRelativa = new DistanciaLejana();

        if ((Math.abs(otraPiezaX-piezaEjeX)+(Math.abs(otraPiezaY-piezaEjeY)))<3) {
            distanciaRelativa = new DistanciaCercana();
        }
        if (((Math.abs(otraPiezaX-piezaEjeX)+(Math.abs(otraPiezaY-piezaEjeY)))>2)&&
                ((Math.abs(otraPiezaX-piezaEjeX)+(Math.abs(otraPiezaY-piezaEjeY)))<6)) {
            distanciaRelativa = new DistanciaMedia();
        }

       return distanciaRelativa;
    };

    public void ejecutarComportamientoPorDistancia(Pieza atacante, Pieza atacado) { }

}