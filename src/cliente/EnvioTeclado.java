package cliente;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import cliente.Conexion;
import medida.*;

public class EnvioTeclado extends Thread {
	public int direccion = Medida.ESTE;
	private Conexion conexion;
	private KeyListener keyListener;
	private boolean stop = false;
	
	public EnvioTeclado(Conexion c) {
		this.conexion = c;
		keyListener = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					direccion = Medida.OESTE;
				}

				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					direccion = Medida.ESTE;
				}

				if (e.getKeyCode() == KeyEvent.VK_UP) {
					direccion = Medida.NORTE;
				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					direccion = Medida.SUR;
				}

			}

			@Override
			public void keyReleased(KeyEvent e) { }

			@Override
			public void keyTyped(KeyEvent e) {	}
		

		};
	}
	@Override
	public void run() {
		long nextGameTick = System.currentTimeMillis();
		long sleepTime = 0;

		while(!stop) {
			conexion.enviarMensaje(new Mensaje(Parametro.MOVIMIENTO, direccion));
			// FPS
			nextGameTick += Medida.SKIP_TICKS;
			sleepTime = nextGameTick - System.currentTimeMillis();
	
			if (sleepTime >= 0) {
	
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} 
		}
	}
	
	public void stopEnvio() {
		stop=false;
	}
}
