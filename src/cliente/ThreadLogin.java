package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import baseDeDatos.Login;

public class ThreadLogin  extends Thread{
//	private  Login gui;
//	private Socket socket;
//	private ObjectInputStream entrada;
//	private Mensaje respuesta;
//	
//	
//     public ThreadLogin(Login gui) throws UnknownHostException, IOException {
//        this.gui = gui; 
//        socket = new Socket("localhost", 10000); 
//        entrada= new ObjectInputStream(socket.getInputStream());
//    }
//
//    @Override
//    public void run() {
//        try {
//        	respuesta=(Mensaje) entrada.readObject();
////            while (respuesta.) { 
//               
//            }
//        } catch (IOException ex) {
//            //eventually exception handling
//            System.err.println(ex);
//        }
//
//    }
}
