package edu.fiuba.algoChess;

import edu.fiuba.algoChess.batallones.Batallon;
import edu.fiuba.algoChess.batallones.BatallonNull;
import edu.fiuba.algoChess.batallones.BatallonUtil;
import edu.fiuba.algoChess.batallones.Batalloneable;
import edu.fiuba.algoChess.excepciones.FueraDeRangoParaEjecutarComportamientoException;
import edu.fiuba.algoChess.excepciones.NoSePuedeAtacarUnAliadoException;
import edu.fiuba.algoChess.salud.Salud;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SoldadoTest {

	@Test
	public void test00SeVerificaBatallon(){

		BandoJugador1 bandoJugador1 = new BandoJugador1();
		BandoJugador2 bandoJugador2 = new BandoJugador2();
		Tablero tableroTest = new Tablero(bandoJugador1, bandoJugador2);


		Soldado soldado1 = new Soldado(new Ubicacion(2,2), bandoJugador1, tableroTest);
		Soldado soldado2 = new Soldado(new Ubicacion(3,2), bandoJugador1, tableroTest);
		Soldado soldado3 = new Soldado(new Ubicacion(4,2), bandoJugador1, tableroTest);

		soldado1.actualizaRango(tableroTest);
		soldado2.actualizaRango(tableroTest);
		soldado3.actualizaRango(tableroTest);

		assertTrue(BatallonUtil.esBatallon(soldado1,soldado2,soldado3));
	}

	@Test
	public void test01TresSoldadosContiguosSeMuevenEnLaMismaDireccion(){


		BandoJugador1 bandoJugador1 = new BandoJugador1();
		BandoJugador2 bandoJugador2 = new BandoJugador2();
		Tablero tableroTest = new Tablero(bandoJugador1, bandoJugador2);

		Soldado soldado1 = new Soldado(new Ubicacion(2,2),bandoJugador1, tableroTest);
		Soldado soldado2 = new Soldado(new Ubicacion(3,2),bandoJugador1, tableroTest);
		Soldado soldado3 = new Soldado(new Ubicacion(4,2),bandoJugador1, tableroTest);

		soldado1.actualizaRango(tableroTest);
		soldado2.actualizaRango(tableroTest);
		soldado3.actualizaRango(tableroTest);

		Batalloneable batallon = BatallonUtil.batallonAsociadoONull(soldado1);
		batallon.moverBatallonArriba(tableroTest);
;
		assertEquals(batallon.getPieza3().getUbicacion(), new Ubicacion(2,3));
		assertEquals(batallon.getPieza1().getUbicacion(), new Ubicacion(3,3));
		assertEquals(batallon.getPieza2().getUbicacion(), new Ubicacion(4,3));

	}

	@Test
	public void test02TresSoldadosConObstaculoSeMuevenDos(){

		BandoJugador1 bandoJugador1 = new BandoJugador1();
		BandoJugador2 bandoJugador2 = new BandoJugador2();
		Tablero tableroTest = new Tablero(bandoJugador1, bandoJugador2);
		Soldado soldado1 = new Soldado(new Ubicacion(2,2), bandoJugador1,tableroTest);
		Soldado soldado2 = new Soldado(new Ubicacion(3,2), bandoJugador1,tableroTest);
		Soldado soldado3 = new Soldado(new Ubicacion(4,2), bandoJugador1,tableroTest);
		Jinete jinete = new Jinete(new Ubicacion(3,3));

		soldado1.setBando(bandoJugador1);
		soldado2.setBando(bandoJugador1);
		soldado3.setBando(bandoJugador1);
		jinete.setBando(bandoJugador1);

		soldado1.actualizaRango(tableroTest);
		soldado2.actualizaRango(tableroTest);
		soldado3.actualizaRango(tableroTest);

		Batalloneable batallon = BatallonUtil.batallonAsociadoONull(soldado1);
		batallon.moverBatallonArriba(tableroTest);

	}

	@Test
	public void test03TresSoldadosConObstaculoDisuelveBatallon(){

		BandoJugador1 bandoJugador1 = new BandoJugador1();
		BandoJugador2 bandoJugador2 = new BandoJugador2();
		Tablero tableroTest = new Tablero(bandoJugador1, bandoJugador2);
		Soldado soldado1 = new Soldado(new Ubicacion(2,2), bandoJugador1,tableroTest);
		Soldado soldado2 = new Soldado(new Ubicacion(3,2), bandoJugador1,tableroTest);
		Soldado soldado3 = new Soldado(new Ubicacion(4,2), bandoJugador1,tableroTest);
		Jinete jinete = new Jinete(new Ubicacion(3,3), bandoJugador1,tableroTest);

		soldado1.setBando(bandoJugador1);
		soldado2.setBando(bandoJugador1);
		soldado3.setBando(bandoJugador1);
		jinete.setBando(bandoJugador1);

		soldado1.actualizaRango(tableroTest);
		soldado2.actualizaRango(tableroTest);
		soldado3.actualizaRango(tableroTest);

		Batalloneable batallon = Batallon.batallonAsociadoONull(soldado1);
		Batalloneable batallonModificado = batallon.moverBatallonArriba(tableroTest);

		assertTrue(batallonModificado.equals(new BatallonNull()));

	}

	@Test
	public void test04CuatroSoldadosContiguosMuevenTres(){

		BandoJugador1 bandoJugador1 = new BandoJugador1();
		BandoJugador2 bandoJugador2 = new BandoJugador2();
		Tablero tableroTest = new Tablero(bandoJugador1, bandoJugador2);
		Soldado soldado1 = new Soldado(new Ubicacion(2,2), bandoJugador1,tableroTest);
		Soldado soldado2 = new Soldado(new Ubicacion(3,2), bandoJugador1,tableroTest);
		Soldado soldado3 = new Soldado(new Ubicacion(4,2), bandoJugador1,tableroTest);
		Soldado soldado4 = new Soldado(new Ubicacion(5,2), bandoJugador1,tableroTest);

		soldado1.setBando(bandoJugador1);
		soldado2.setBando(bandoJugador1);
		soldado3.setBando(bandoJugador1);
		soldado4.setBando(bandoJugador1);

		soldado1.actualizaRango(tableroTest);
		soldado2.actualizaRango(tableroTest);
		soldado3.actualizaRango(tableroTest);
		soldado4.actualizaRango(tableroTest);

		Batalloneable batallon = BatallonUtil.batallonAsociadoONull(soldado1);
		batallon.moverBatallonArriba(tableroTest);

		assertEquals(batallon.getPieza1().getUbicacion(), new Ubicacion(3,3));
		assertEquals(batallon.getPieza2().getUbicacion(), new Ubicacion(4,3));
		assertEquals(batallon.getPieza3().getUbicacion(), new Ubicacion(2,3));
		assertEquals(soldado4.getUbicacion(), new Ubicacion(5,2));
	};

	@Test
	public void test05SoldadoAliadoAtacaJineteEnemigoCercano(){
		BandoJugador1 bandoJugador1 = new BandoJugador1();
		BandoJugador2 bandoJugador2 = new BandoJugador2();
		Tablero tablero = new Tablero(bandoJugador1, bandoJugador2);
		Ubicacion ubicacionSoldado = new Ubicacion(10,1);
		Soldado soldadoAliado = new Soldado(ubicacionSoldado,bandoJugador1);
		Ubicacion ubicacionJinete = new Ubicacion(11,1);
		Jinete jineteEnemigo = new Jinete(ubicacionJinete,bandoJugador2);
		tablero.ubicarEnCelda(jineteEnemigo,ubicacionJinete);
		tablero.ubicarEnCelda(soldadoAliado, ubicacionSoldado);
		int vidaTrasAtaque = jineteEnemigo.getVida().getValorActual() - soldadoAliado.getAtaqueCercano().getValorComportamiento();
		soldadoAliado.atacar(jineteEnemigo);
		assertEquals(vidaTrasAtaque,jineteEnemigo.getVida().getValorActual());
	};

	@Test (expected = FueraDeRangoParaEjecutarComportamientoException.class)
	public void test06SeArrojaExceptionCuandoSeQuiereAtacarAUnEnemigoLejano(){
		BandoJugador1 bandoJugador1 = new BandoJugador1();
		BandoJugador2 bandoJugador2 = new BandoJugador2();
		Tablero tablero = new Tablero(bandoJugador1, bandoJugador2);
		Ubicacion ubicacionSoldado = new Ubicacion(10,1);
		Soldado soldadoAliado = new Soldado(ubicacionSoldado,bandoJugador1);
		Ubicacion ubicacionJinete = new Ubicacion(11,10);
		Jinete jineteEnemigo = new Jinete(ubicacionJinete,bandoJugador2);
		tablero.ubicarEnCelda(jineteEnemigo,ubicacionJinete);
		tablero.ubicarEnCelda(soldadoAliado, ubicacionSoldado);
		soldadoAliado.atacar(jineteEnemigo);
	};

	@Test (expected = FueraDeRangoParaEjecutarComportamientoException.class)
	public void test07SeArrojaExceptionCuandoSeQuiereAtacarAUnEnemigoADistanciaMedia(){

		BandoJugador1 bandoJugador1 = new BandoJugador1();
		BandoJugador2 bandoJugador2 = new BandoJugador2();
		Tablero tablero = new Tablero(bandoJugador1, bandoJugador2);
		Ubicacion ubicacionSoldado = new Ubicacion(10,1);
		Soldado soldadoAliado = new Soldado(ubicacionSoldado,bandoJugador1);
		Ubicacion ubicacionJinete = new Ubicacion(11,3);
		Jinete jineteEnemigo = new Jinete(ubicacionJinete,bandoJugador2);
		tablero.ubicarEnCelda(jineteEnemigo,ubicacionJinete);
		tablero.ubicarEnCelda(soldadoAliado, ubicacionSoldado);
		soldadoAliado.atacar(jineteEnemigo);
	};

	@Test (expected = NoSePuedeAtacarUnAliadoException.class)
	public void test08SeArrojaExceptionCuandoSeQuiereAtacarUnAliado(){
		BandoJugador1 bandoJugador1 = new BandoJugador1();
		BandoJugador2 bandoJugador2 = new BandoJugador2();
		Tablero tablero = new Tablero(bandoJugador1, bandoJugador2);
		Ubicacion ubicacionSoldado = new Ubicacion(10,1);
		Soldado soldadoAliado = new Soldado(ubicacionSoldado,bandoJugador1);
		Ubicacion ubicacionJinete = new Ubicacion(10,2);
		Jinete jineteAliado = new Jinete(ubicacionJinete,bandoJugador1);
		tablero.ubicarEnCelda(jineteAliado,ubicacionJinete);
		tablero.ubicarEnCelda(soldadoAliado, ubicacionSoldado);
		soldadoAliado.atacar(jineteAliado);
	};

}
