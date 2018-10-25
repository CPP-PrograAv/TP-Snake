package TecladoEvento;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import medida.*;

public class InputTeclado implements KeyListener{

	public int direccion = 0;
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			direccion = Medida.OESTE;
		//	System.out.println("Entro LEFT");
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			direccion = Medida.ESTE;
		//	System.out.println("Entro RIGHT");
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			direccion = Medida.NORTE;
		//	System.out.println("Entro UP");	
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			direccion = Medida.SUR;
		//	System.out.println("Entro DOWN");
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
