package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import base.DAOServidor;
import base.Juego;
import base.Jugador;
import base.Tablero;
import baseDeDatos.Persona;
import cliente.Mensaje;
import medida.Parametro;
import ui.SalaEspera;

public class EscuchaCliente extends Thread {

	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private int datosTeclado;
	private Tablero laminaJuego;
	private Persona persona;
	private Mensaje paqueteDatos;
	private Integer numSala = null;
	private Socket cliente;
	private Socket clienteOut;
	private Servidor server;

	public EscuchaCliente(ObjectInputStream entrada, ObjectOutputStream salida, Socket cliente, Socket clienteOut,
			Servidor server) {
		this.cliente = cliente;
		this.clienteOut = clienteOut;
		this.entrada = entrada;
		this.salida = salida;
		this.server = server;
	}

	@Override
	public void run() {
		ArrayList<EscuchaCliente> clientesEnSala;
		long tiempo = System.currentTimeMillis();
		long dt = 0;

		while (dt < 2 * 60 * 60 * 1000) {
			try {
				paqueteDatos = (Mensaje) entrada.readObject();

				switch (paqueteDatos.getCod()) {
				case Parametro.LOGGEO:
					persona = (Persona) paqueteDatos.getDato();
					DAOServidor daoserver = new DAOServidor();
					List<Object[]> persona2 = daoserver.realizarLogin(persona);

					Persona perAux = new Persona();

					if (persona2.size() == 1) {
						perAux.setMail(persona2.get(0)[0].toString());
						perAux.setContraseña(persona2.get(0)[1].toString());
						perAux.setNick(persona2.get(0)[2].toString());
					}

					salida.flush();
					salida.writeObject(perAux);

					break;

				case Parametro.NUEVASALA:

					persona = (Persona) paqueteDatos.getDatoAux();
					SalaEspera sala = (SalaEspera) paqueteDatos.getDato();
					server.agregarListaSalas(sala, numSala);
					server.agregarListaPersonas(persona);
					server.agregarListaJugador(new Jugador(persona.getNick(), persona.getPuntaje()));
					server.agregarSala(this, numSala);
					boolean flag = true;
					salida.flush();
					salida.writeObject(flag);

					break;

				case Parametro.REGISTRARSE:
					persona = (Persona) paqueteDatos.getDato();
					DAOServidor daoserver1 = new DAOServidor();
					int respuesta = daoserver1.realizarRegistro(persona);

					salida.flush();
					salida.writeObject(respuesta);

					break;

				case Parametro.SALIO_JUGADOR:
					// No hace falta pasar la persona ya que el thread es de una
					// persona
					persona = (Persona) paqueteDatos.getDato();
					server.removerListaPersonas(persona);
					server.removerClienteDeSala(numSala, this);
					clientesEnSala = server.getClientesSala(numSala);
					for (EscuchaCliente ec : clientesEnSala) {
						ec.salida.flush();
						ec.salida.writeObject(new Mensaje(Parametro.SALIO_JUGADOR, ec.persona));
					}
					break;

				case Parametro.ACTUALIZAR_LOBBY:

					HashMap<Integer, SalaEspera> hSalas = server.gethSalas();
					salida.flush();
					salida.writeObject(hSalas);

					// entrada.close();

					break;
				case Parametro.UNIRSE:
					persona = (Persona) paqueteDatos.getDato();
					numSala = paqueteDatos.getIndice();
					server.getSala(numSala).agregarJugador(persona);
					server.agregarClienteASala(numSala, this);
					// entrada.close();
					clientesEnSala = server.getClientesSala(numSala);

					for (EscuchaCliente ec : clientesEnSala) {
						ec.salida.flush();
						ec.salida.writeObject(server.getSala(paqueteDatos.getIndice()));
					}
					server.agregarListaPersonas(persona);
					server.agregarListaJugador(new Jugador(persona.getNick(), persona.getPuntaje()));
					break;

				case Parametro.EMPEZAR_JUEGO:
					clientesEnSala = server.getClientesSala(numSala);
					Juego juego = new Juego();
					for (EscuchaCliente ec : clientesEnSala) {
						juego.agregarJugador(new Jugador(ec.getName(),0));
						ec.salida.flush();
						ec.salida.writeObject(new Mensaje(Parametro.EMPEZAR_JUEGO,juego));
					}
					break;
				case Parametro.SOLICITAR_SALA:
					int num = server.solicitarIndiceSala();
					numSala = num;
					salida.flush();
					salida.writeObject(num);
					break;
				default:
					break;
				}
			} catch (IOException | ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		}
	}
}