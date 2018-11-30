package ui;

import java.awt.Component;

import baseDeDatos.Persona;
import cliente.*;
import medida.Parametro;

public class SalaEsperaHilo extends Thread {

	private Conexion salaConexion;
	private SalaEspera sala;

	public SalaEsperaHilo(Conexion salaCon, SalaEspera sala) {
		this.salaConexion = salaCon;
		this.sala = sala;
	}

	@Override
	public void run() {

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
				System.out.println("RESPONDI SALIR JUGADOR");
				break;
			case Parametro.SE_UNIO_JUGADOR:
				sala.agregarJugador((Persona) msj.getDato());
				break;
			default:
				break;
			}
		}

	}

}
