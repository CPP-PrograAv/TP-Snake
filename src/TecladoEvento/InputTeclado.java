package TecladoEvento;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import cliente.Conexion;
import medida.*;

public class InputTeclado implements KeyListener  {

	public int direccion = 0;
	private Conexion datosTeclado ;
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			direccion = Medida.OESTE;
			//datosTeclado = new Conexion(direccion);
			
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			direccion = Medida.ESTE;
			//datosTeclado = new Conexion(direccion);
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			direccion = Medida.NORTE;
			//datosTeclado = new Conexion(direccion);
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			direccion = Medida.SUR;
			//datosTeclado = new Conexion(direccion);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

}
