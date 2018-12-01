package GameObjects;

import java.awt.*;
import base.*;
import medida.*;

public class Fruta extends GameObject {
	private Tablero tablero;
	public static int IdItem = -1;
	public static int cantidad = 0; // lo utilizo para controlar la cantidad permitida que debe haber en el tablero

	public Fruta(Tablero tablero) {
		super(ubicar(tablero), IdItem, tablero);
		cantidad++;
		this.tablero = tablero;
	}

	public Fruta(Punto punto, Tablero tablero) {
		super(punto, IdItem, tablero);
		cantidad++;
	}

	/**
	 * ubico la fruta si la cantidad de frutas que estan instanciadas no supera el
	 * limite.
	 */

	public void setItem() {

		tablero.matriz[getPosX()][getPosY()] = 0;
		tablero.tablero[getPosX()][getPosY()] = null;

		if (cantidad < Tablero.cantidadFruta) {
			setPosition(ubicar(tablero), IdItem);
			cantidad++;
		}

	}

	@Override
	public void paint(Graphics2D g2d) {
		g2d.setColor(Color.GREEN); // la fruta siempre sera verde
		int padding = Medida.BORDE / 2;
		g2d.fillRect(this.getPosX() * Medida.SIZE + 1 + padding, this.getPosY() * Medida.SIZE + 1 + padding,
				Medida.SIZE - 2, Medida.SIZE - 2);

	}

	/**
	 * Genera un punto al azar donde la matriz del escenario no contiene nada
	 * WARNING: si no hay lugar me quedo trabado
	 * 
	 * @return Punto
	 */
	public static Punto ubicar(Tablero tablero) {
		Punto p1;
		do {
			p1 = new Punto((int) (Math.random() * (Medida.ANCHO / Medida.SIZE - 1)),
					(int) (Math.random() * (Medida.LARGO / Medida.SIZE - 1)));
			// Si no encuentro lugar me quedo generando un punto donde haya
		} while (tablero.matriz[p1.getX()][p1.getY()] != 0 || tablero.tablero[p1.getX()][p1.getY()] != null);
		return p1;
	}

	@Override
	public void accionColision(Snake snake) {
		snake.crecer();
		cantidad--; // al ser comido descuento la cantidad
		this.setItem();
	}

}
