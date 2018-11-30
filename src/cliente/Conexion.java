package cliente;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import baseDeDatos.Persona;
import medida.*;
import ui.Sala;
import ui.SalaEspera;

public class Conexion {

	private ObjectOutputStream salida;
	private ObjectInputStream entrada;

	private Socket socket;
	private Socket socketOut;

	public Conexion(Socket socket, Socket socketOut) {

		try {
			this.socket = socket;
			this.socketOut = socketOut;
			salida = new ObjectOutputStream(this.socket.getOutputStream());
			entrada = new ObjectInputStream(this.socketOut.getInputStream());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Mensaje escucharMensaje() {

		Mensaje msj = null;

		try {
			msj = (Mensaje) entrada.readObject();
			return msj;

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return msj;
	}

	public Persona loguear(Mensaje paqueteDatos) {

		try {
			salida.flush();
			salida.writeObject(paqueteDatos); // mando msj
			Persona per = (Persona) entrada.readObject();
			return per;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;

	}

	public boolean crearSala(Mensaje paqueteDatos) {

		try {

			salida.flush();
			salida.writeObject(paqueteDatos);
			boolean flag = (boolean) entrada.readObject();
			System.out.println("FLAG "+ flag);
			return  flag;

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void solicitarSalir(Mensaje paqueteDatos) {

		try {
			salida.flush();
			salida.writeObject(paqueteDatos);
		} catch (Exception e) {
		}
	}

	public int registrarse(Mensaje paqueteDatos) {

		int resp = 0;
		try {
			salida.flush();
			salida.writeObject(paqueteDatos);
			resp = (int) entrada.readObject();
			return resp;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return resp;
	}

	public ArrayList<SalaEspera> actualizarLobby(Mensaje paqueteDatos) {

		try {
			salida.flush();
			salida.writeObject(paqueteDatos);
			return (ArrayList<SalaEspera>) entrada.readObject();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	public SalaEspera UnirseSala(Mensaje paqueteDatos) {

		try {
			salida.flush();
			salida.writeObject(paqueteDatos);

			SalaEspera sala = (SalaEspera) entrada.readObject();
			return sala;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean empezarJuego(Mensaje paqueteDatos) {

		try {
			salida.flush();
			salida.writeObject(paqueteDatos);
		} catch (IOException e) {
		}

		return true;
	}

}
