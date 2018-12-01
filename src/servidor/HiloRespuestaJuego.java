package servidor;

import base.Juego;
import medida.Medida;

public class HiloRespuestaJuego extends Thread {
	private EscuchaCliente ec;
	private Juego j;

	public HiloRespuestaJuego(EscuchaCliente ec, Juego j) {
		this.ec = ec;
		this.j = j;
	}

	@Override
	public void run() {
		long nextGameTick = System.currentTimeMillis();
		long sleepTime = 0;

		while (j.getEnJuego()) {
			ec.actualizarJuego(j);
			// FPS
			nextGameTick += Medida.SKIP_TICKS;
			sleepTime = nextGameTick - System.currentTimeMillis();
			if (sleepTime >= 0) {
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.getMessage();
				}
			}
		}
	}
}
