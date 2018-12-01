package ui;

import java.awt.Component;

import base.Juego;
import baseDeDatos.Persona;
import cliente.*;
import medida.Parametro;
import ventanaCliente.VentanaJuego;

public class SalaEsperaHilo extends Thread {

	private Conexion salaConexion;
	private SalaEspera sala;

	public SalaEsperaHilo(Conexion salaCon, SalaEspera sala) {
		this.salaConexion = salaCon;
		this.sala = sala;
	}

	@Override
	public void run() {
		Juego juego=null;
		VentanaJuego juegoCliente = null;
		while (true) {
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Mensaje msj = salaConexion.escucharMensaje();
			switch (msj.getCod()) {
			case Parametro.SALIO_JUGADOR:
				sala.sacarJugador((Persona) msj.getDato());
				break;
			case Parametro.SE_UNIO_JUGADOR:
				sala.agregarJugador((Persona) msj.getDatoAux());
				break;
			case Parametro.EMPEZAR_JUEGO:
				//VisualizadorJuego O Juego "Cliente"
				juego = (Juego) msj.getDato();
				juegoCliente = new VentanaJuego(juego /*,salaConexion*/ );
				EnvioTeclado escuchaTeclado = new EnvioTeclado(salaConexion);
				juegoCliente.setVisible(true);
				escuchaTeclado.start();
			case Parametro.ACTUALIZAR_JUEGO:
				juego = (Juego) msj.getDato();
				juegoCliente.setJuego(juego);
				break;
			default:
				break;
			}
		}

	}

}
