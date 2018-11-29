package Servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import TecladoEvento.InputTeclado;
import base.DAOServidor;
import base.Tablero;
import baseDeDatos.Persona;
import medida.Medida;

public class Servidor implements Runnable {

	private int puerto;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private int datosTeclado;
	private Tablero laminaJuego;
	private Persona persona;
	private ServerSocket servidor;
	private Persona perAux;
	public Servidor() {

		Thread hilo = new Thread(this);
		hilo.start();
	}

	@Override
	public void run() {

		try {
			ServerSocket servidorIn = new ServerSocket(Medida.PUERTO1);
			ServerSocket servidorOut = new ServerSocket(Medida.PUERTO2);

			while (true) {

				System.out.println("SERVIDOR INICIADO");
				Socket cliente = servidorIn.accept();
				Socket clienteOut = servidorOut.accept();

				entrada = new ObjectInputStream(cliente.getInputStream());
				salida = new ObjectOutputStream(clienteOut.getOutputStream());

				persona = (Persona) entrada.readObject();

				DAOServidor daoserver = new DAOServidor();
				List<Object[]> persona2 = daoserver.realizarLogin(persona);

				perAux = new Persona();
				
				if(persona2.size()==1) {
					perAux.setMail(persona2.get(0)[0].toString());
					perAux.setContraseña(persona2.get(0)[1].toString());
					perAux.setNick(persona2.get(0)[2].toString());
					
				}
				

				salida.flush();
				salida.writeObject(perAux);

				entrada.close();

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

}
