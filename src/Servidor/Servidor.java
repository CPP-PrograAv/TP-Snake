package Servidor;

import java.io.*;
import java.net.*;
import java.util.*;

import base.*;
import baseDeDatos.Persona;
import cliente.Mensaje;
import medida.*;
import ui.SalaEspera;

public class Servidor implements Runnable {

	private int puerto;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private int datosTeclado;
	private Tablero laminaJuego;
	private Persona persona;
	private ServerSocket servidor;
	private Persona perAux;
	private Mensaje paqueteDatos;
	private ArrayList<SalaEspera> vSalaEspera = new ArrayList<SalaEspera>();
	private ArrayList<Jugador> vJugador = new ArrayList<Jugador>();

	public Servidor() {

		Thread hilo = new Thread(this);
		hilo.start();
	}

	@Override
	public void run() {

		try {
			ServerSocket servidorIn = new ServerSocket(Parametro.PUERTO1);
			ServerSocket servidorOut = new ServerSocket(Parametro.PUERTO2);

			while (true) {

				System.out.println("SERVIDOR INICIADO");
				Socket cliente = servidorIn.accept();
				Socket clienteOut = servidorOut.accept();

				entrada = new ObjectInputStream(cliente.getInputStream());
				salida = new ObjectOutputStream(clienteOut.getOutputStream());

				paqueteDatos = (Mensaje) entrada.readObject();

				switch (paqueteDatos.getCod()) {
				case Parametro.LOGGEO:
					persona = (Persona) paqueteDatos.getDato();
					DAOServidor daoserver = new DAOServidor();
					List<Object[]> persona2 = daoserver.realizarLogin(persona);

					perAux = new Persona();

					if (persona2.size() == 1) {
						perAux.setMail(persona2.get(0)[0].toString());
						perAux.setContrase�a(persona2.get(0)[1].toString());
						perAux.setNick(persona2.get(0)[2].toString());
					}
					salida.flush();
					salida.writeObject(perAux);

					entrada.close();

					break;

				case Parametro.NUEVASALA:
					persona = (Persona) paqueteDatos.getDato();
					SalaEspera sala = new SalaEspera(paqueteDatos.getCadena(), persona);
					vSalaEspera.add(sala);
					vJugador.add(new Jugador(persona.getNick()));
					salida.flush();
					salida.writeObject(sala);
					entrada.close();
					break;

				case Parametro.REGISTRARSE:
					persona = (Persona) paqueteDatos.getDato();
					DAOServidor server = new DAOServidor();
					int respuesta = server.realizarRegistro(persona);

					salida.flush();
					salida.writeObject(respuesta);
					entrada.close();

					break;

				case Parametro.ACTUALIZAR_LOBBY:

					ArrayList<SalaEspera> salas = this.getvSalaEspera();
					salida.flush();
					salida.writeObject(salas);
					entrada.close();

					break;
				case Parametro.UNIRSE:
					vSalaEspera.get(paqueteDatos.getIndice()).a�adirJugador((Persona) paqueteDatos.getDato());
					salida.flush();
					salida.writeObject(vSalaEspera.get(paqueteDatos.getIndice()));
					entrada.close();
					
					break;
					
				case Parametro.EMPEZAR_JUEGO:
					
					
					
					
					break;
				
				default:
					break;
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		Servidor servidor = new Servidor();
	}

	public ArrayList<SalaEspera> getvSalaEspera() {
		return vSalaEspera;
	}

	public void setvSalaEspera(ArrayList<SalaEspera> vSalaEspera) {
		this.vSalaEspera = vSalaEspera;
	}

}
