package cliente;

import java.io.*;
import java.net.*;

import baseDeDatos.Persona;
import medida.*;

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

	public Persona loguear(Persona persona) {

		try {
			salida = new ObjectOutputStream(this.socket.getOutputStream());
			salida.flush();
			salida.writeObject(persona); //mando msj
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

//	@Override
//	public void run() {
////
//		Persona persona;
//
//		try {
//			
//			entrada = new ObjectInputStream(socketOut.getInputStream());
//			
//			
//			
////			Socket cliente;
////			ServerSocket servidor = new ServerSocket(9000);
////
////			while (true) {
////				cliente = servidor.accept();
////				entrada = new ObjectInputStream(cliente.getInputStream());
////				persona = (Persona) entrada.readObject();
////				
////			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

}
