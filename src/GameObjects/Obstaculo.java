package GameObjects;

import java.awt.*;
import java.util.ArrayList;

import base.*;
import medida.*;

public class Obstaculo extends GameObject {
	public static int IdObstaculo = -2;
	private Tablero tablero;
	private ArrayList<Obstaculo> cuerpoObstaculo = new ArrayList<Obstaculo>();

	public Obstaculo(Tablero tablero) {
		super(ubicar(tablero), IdObstaculo, tablero);
		this.tablero = tablero;
		int obstaculoCreceIzquierda = 1;
		int obstaculoCreceArriba = 1;
		int obstaculoAcostado = (int) (Math.random() * 100);// genero un numero random para que el obstaculo sea
															// horizontal o vertical
		System.out.println(obstaculoAcostado);
		if (obstaculoAcostado >= 50) {
			for (int i = 1; i <= 8; i++) {
				if (getPosX() + i < 50 && tablero.matriz[getPosX() + i][getPosY()] != IdObstaculo
						&& !(tablero.tablero[getPosX() + i][getPosY()] instanceof Snake)) // 50 * 50 TAM de la matriz
					cuerpoObstaculo.add(new Obstaculo(new Punto(getPosX() + i, getPosY()), IdObstaculo, tablero));
				else if ((getPosX() - obstaculoCreceIzquierda) > 0
						&& tablero.matriz[getPosX() - obstaculoCreceIzquierda][getPosY()] != IdObstaculo
						&& !(tablero.tablero[getPosX() - obstaculoCreceIzquierda][getPosY()] instanceof Snake)) { // valido
																													// que
																													// en
																													// la
																													// posicion
																													// que
																													// voy
																													// a
																													// poner
																													// el
																													// obstaculo,
																													// no
																													// exista
																													// otro
																													// obstaculo
					cuerpoObstaculo
							.add(new Obstaculo(new Punto(getPosX() - obstaculoCreceIzquierda, getPosY()), IdObstaculo, tablero));
					obstaculoCreceIzquierda++;
				} else
					break;
			}
		} else {
			for (int i = 1; i <= 8; i++) {
				if (getPosY() + i < 50 && tablero.matriz[getPosX()][getPosY() + i] != IdObstaculo
						&& !(tablero.tablero[getPosX()][getPosY() + i] instanceof Snake)) // 50 * 50 TAM de la matriz
					cuerpoObstaculo.add(new Obstaculo(new Punto(getPosX(), getPosY() + i), IdObstaculo,tablero));
				else if ((getPosY() - obstaculoCreceArriba) > 0
						&& tablero.matriz[getPosX()][getPosY() - obstaculoCreceArriba] != IdObstaculo
						&& !(tablero.tablero[getPosX()][getPosY() - obstaculoCreceArriba] instanceof Snake)) {
					cuerpoObstaculo
							.add(new Obstaculo(new Punto(getPosX(), getPosY() - obstaculoCreceArriba), IdObstaculo, tablero));
					obstaculoCreceArriba++;
				} else
					break;
			}
		}
	}

	public Obstaculo(Punto punto, int id, Tablero tablero) {
		super(punto, id, tablero);
	}

	private static Punto ubicar(Tablero tablero) {
		Punto p1;
		do {
			p1 = new Punto((int) (Math.random() * (Medida.ANCHO / Medida.SIZE - 1)),
					(int) (Math.random() * (Medida.LARGO / Medida.SIZE - 1)));
			// Si no encuentro lugar me quedo generando un punto donde haya
		} while (tablero.matriz[p1.getX()][p1.getY()] != 0 || tablero.tablero[p1.getX()][p1.getY()] != null);

		return p1;
	}

	@Override
	public void paint(Graphics2D g2d) {
		g2d.setColor(Color.ORANGE);// el obstaculo siempre sera naranja
		int padding = Medida.BORDE / 2;
		g2d.fillRect(this.getPosX() * Medida.SIZE + 1 + padding, this.getPosY() * Medida.SIZE + 1 + padding,
				Medida.SIZE, Medida.SIZE);
	}

	@Override
	public void accionColision(Snake snake) {
		snake.morir();
	}
}
