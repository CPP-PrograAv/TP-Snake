package servidor;

import java.io.*;
import java.net.*;
import java.util.*;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Single;

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
	private ArrayList<Persona> vPersonas = new ArrayList<Persona>();
	private HashMap<Integer,ArrayList<EscuchaCliente>> clientesPorSala = new HashMap();
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


	public ArrayList<SalaEspera> getvSalaEspera() {
		return vSalaEspera;
	}

	public void setvSalaEspera(ArrayList<SalaEspera> vSalaEspera) {
		this.vSalaEspera = vSalaEspera;
	}

	synchronized public void removerListaJugador(Jugador jugador) {
		vJugador.remove(jugador);
	}

	synchronized public void removerListaSalas(SalaEspera sala) {
		vSalaEspera.remove(sala);
	}

	synchronized public void removerListaPersonas(Persona persona) {
		vPersonas.remove(persona);
	}

	synchronized public void removerListaJugador(int indice) {
		vJugador.remove(indice);
	}

	synchronized public void removerListaSalas(int indice) {
		vSalaEspera.remove(indice);
	}

	synchronized public void removerListaPersonas(int indice) {
		vPersonas.remove(indice);
	}

	synchronized public Jugador getJugador(int indice) {
		return vJugador.get(indice);
	}

	synchronized public SalaEspera getSala(int indice) {
		return vSalaEspera.get(indice);
	}

	synchronized public Persona getPersona(int indice) {
		return vPersonas.get(indice);
	}

	synchronized public void agregarListaJugador(Jugador jugador) {
		vJugador.add(jugador);
	}

	synchronized public void agregarListaSalas(SalaEspera sala) {
		vSalaEspera.add(sala);
	}

	synchronized public void agregarListaPersonas(Persona persona) {
		vPersonas.add(persona);
	}
	
	synchronized public int agregarSala(EscuchaCliente ec) {
		ArrayList<EscuchaCliente> lista = new ArrayList<>();
		lista.add(ec);
		clientesPorSala.put(numSala, lista);
		return numSala;
	}
	synchronized public boolean agregarClienteASala(int sala, EscuchaCliente ec) {
		ArrayList<EscuchaCliente> lista = clientesPorSala.get(sala);
		if(lista!=null){
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
		if(lista.size()==0)
			clientesPorSala.remove(sala);
	}

	public int solicitarIndiceSala() {
		return numSala++;
	}

	public static void main(String[] args) {
		Servidor servidor = new Servidor();
	}
}
