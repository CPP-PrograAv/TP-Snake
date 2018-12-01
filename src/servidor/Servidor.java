package servidor;

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
	//private ArrayList<SalaEspera> vSalaEspera = new ArrayList<SalaEspera>();
	private ArrayList<Jugador> vJugador = new ArrayList<Jugador>();
	private ArrayList<Persona> vPersonas = new ArrayList<Persona>();
	private HashMap<Integer, ArrayList<EscuchaCliente>> clientesPorSala = new HashMap();
	private HashMap<Integer, SalaEspera> hSalas = new HashMap<>();

	private static int numSala = 0;

	public Servidor() {
		Thread hilo = new Thread(this);
		hilo.start();
	}

	@Override
	public void run() {

		ServerSocket servidorIn = null;
		ServerSocket servidorOut = null;
		try {
			servidorIn = new ServerSocket(Parametro.PUERTO1);
			servidorOut = new ServerSocket(Parametro.PUERTO2);
			System.out.println("SERVIDOR INICIADO");
		} catch (IOException e1) {
			e1.printStackTrace();
			System.exit(-3);
		}
		while (true) {
			try {
				Socket cliente = servidorIn.accept();
				Socket clienteOut = servidorOut.accept();

				entrada = new ObjectInputStream(cliente.getInputStream());
				salida = new ObjectOutputStream(clienteOut.getOutputStream());

				EscuchaCliente hilo = new EscuchaCliente(entrada, salida, cliente, clienteOut, this);
				hilo.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	

	public HashMap<Integer, SalaEspera> gethSalas() {
		return hSalas;
	}

	public void sethSalas(HashMap<Integer, SalaEspera> hSalas) {
		this.hSalas = hSalas;
	}

	synchronized public void removerListaJugador(Jugador jugador) {
		vJugador.remove(jugador);
	}

	synchronized public void removerListaSalas(int key) {
		hSalas.remove(key);
	}

	synchronized public void removerListaPersonas(Persona persona) {
		vPersonas.remove(persona);
	}

	synchronized public void removerListaJugador(int indice) {
		vJugador.remove(indice);
	}

	/*synchronized public void removerListaSalas(int indice) {
		vSalaEspera.remove(indice);
	}*/

	synchronized public void removerListaPersonas(int indice) {
		vPersonas.remove(indice);
	}

	synchronized public Jugador getJugador(int indice) {
		return vJugador.get(indice);
	}

	synchronized public SalaEspera getSala(int indice) {
		 return hSalas.get(indice);
	}

	synchronized public Persona getPersona(int indice) {
		return vPersonas.get(indice);
	}

	synchronized public void agregarListaJugador(Jugador jugador) {
		vJugador.add(jugador);
	}

	synchronized public void agregarListaSalas(SalaEspera sala,int key) {
		hSalas.put(key, sala);
	}

	synchronized public void agregarListaPersonas(Persona persona) {
		vPersonas.add(persona);
	}

	synchronized public void agregarSala(EscuchaCliente ec,int numSala) {
		ArrayList<EscuchaCliente> lista = new ArrayList<>();
		lista.add(ec);
		clientesPorSala.put(numSala, lista);
		
	}

	synchronized public boolean agregarClienteASala(int sala, EscuchaCliente ec) {
		ArrayList<EscuchaCliente> lista = clientesPorSala.get(sala);
		if (lista != null) {
			lista.add(ec);
			return true;
		}
		return false;
	}

	synchronized public ArrayList<EscuchaCliente> getClientesSala(int sala) {
		return clientesPorSala.get(sala);
	}

	synchronized public void removerSala(int sala) {
		clientesPorSala.remove(sala);
	}

	synchronized public void removerClienteDeSala(int sala, EscuchaCliente ec) {
		ArrayList<EscuchaCliente> lista = clientesPorSala.get(sala);
		lista.remove(ec);
		if (lista.size() == 0)
			clientesPorSala.remove(sala);
	}

	public int solicitarIndiceSala() {
		return numSala++;
	}

	public static void main(String[] args) {
		Servidor servidor = new Servidor();
	}
}