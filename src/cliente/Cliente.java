package cliente;

import java.io.IOException;
import java.net.Socket;

import medida.Parametro;
import ui.Multiplayer;

public class Cliente {

	private static Conexion conexion;
	private static Socket socket;
	private static Socket socketOut;
	public Cliente() {
	
		try {
			socket = new Socket("localhost", Parametro.PUERTO1);
			socketOut = new Socket("localhost", Parametro.PUERTO2);
			conexion = new Conexion(socket, socketOut);
			Multiplayer bienvenida = new Multiplayer();
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public static Conexion getConexion() {
		return conexion;
	}
	
}
