package GameObjects;

import java.awt.*;
import base.*;
import medida.*;

public class Cuerpo extends GameObject  {
	private Tablero tablero;
	private Punto ubicacion;

	public Cuerpo(Tablero tablero) {
		super(new Punto(1, 1), 99, tablero);// ponerlo atras de todo
		this.tablero=tablero;
	}

	public Cuerpo(Punto punto, Tablero tablero) {
		super(new Punto(punto.getX() , punto.getY()), 99, tablero);
		tablero.matriz[punto.getX() ][punto.getY()] = 0;
		tablero.tablero[punto.getX() ][punto.getY()] = null;
		this.tablero = tablero;
	}

	public Cuerpo(Punto ubicacion, int id, Tablero tablero) {
		super(ubicacion, id, tablero);
		this.tablero = tablero;
	}

	public Cuerpo(int x, int y, Tablero tablero) {
		super(new Punto(x, y), 1, tablero);
		this.tablero = tablero;

	}

	public Punto getUbicacion() {
		return this.ubicacion;
	}

	@Override
	public void paint(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);// el cuerpo siempre sera negro
		int padding = Medida.BORDE / 2;
		g2d.fillRect(this.getPosX() * Medida.SIZE + 1 + padding, this.getPosY() * Medida.SIZE + 1 + padding,
				Medida.SIZE - 2, Medida.SIZE - 2);
	}

	@Override
	public void accionColision(Snake snake) {
		snake.morir();
		
	}

	

}
