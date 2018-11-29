package cliente;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import baseDeDatos.Persona;
import medida.*;
import ui.Sala;

public class Conexion{

	private ObjectOutputStream salida;
	private ObjectInputStream entrada;

	private Socket socket;
	private Socket socketOut;

	public Conexion() {
		
		try {
			socket = new Socket("localhost",Parametro.PUERTO1);
			socketOut = new Socket("localhost", Parametro.PUERTO2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Persona loguear(Mensaje paqueteDatos) {

		try {
					
			salida = new ObjectOutputStream(this.socket.getOutputStream());
			salida.flush();
			salida.writeObject(paqueteDatos); //mando msj
//			socket.close();
			
			entrada = new ObjectInputStream(socketOut.getInputStream());
			Persona per = (Persona) entrada.readObject();
			
			return per;
			
//			return persona;
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

public  boolean crearSala(Mensaje paqueteDatos) {
	
	try {
		salida = new ObjectOutputStream(this.socket.getOutputStream());
		salida.flush();
		salida.writeObject(paqueteDatos);
		entrada = new ObjectInputStream(socketOut.getInputStream());
		return 	entrada.readBoolean();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
	
}

}
