package GameObjects;

import java.awt.*;
import base.*;
import medida.*;

public class Cuerpo extends GameObject {

	private Punto ubicacion;

	public Cuerpo() {
		super(new Punto(1, 1), 99);// ponerlo atras de todo
	}

	public Cuerpo(Punto punto) {
		super(new Punto(punto.getX() , punto.getY()), 99);
		Tablero.matriz[punto.getX() ][punto.getY()] = 0;
		Tablero.tablero[punto.getX() ][punto.getY()] = null;

	}

	public Cuerpo(Punto ubicacion, int id) {
		super(ubicacion, id);
	}

	public Cuerpo(int x, int y) {
		super(new Punto(x, y), 1);
	}

	public Punto getUbicacion() {
		return this.ubicacion;
	}

	@Override
	public void paint(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		int padding = Medida.BORDE / 2;
		g2d.fillRect(this.getPosX() * Medida.SIZE + 1 + padding, this.getPosY() * Medida.SIZE + 1 + padding,
				Medida.SIZE - 2, Medida.SIZE - 2);
	}

}
