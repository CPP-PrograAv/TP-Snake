package base;

import GameObjects.*;
import medida.Medida;

public class Colision {

	/**
	 * Evalua la colision entre la snake y la posicion indicada (considera que la
	 * posicion enviada es la siguiente que va a tomar la snake)
	 * 
	 * @param posicion X
	 * @param posicion Y
	 * @param Snake    que se mueve
	 */
	public static void colisionTablero(int x, int y, Snake viboritaQueSeMueve) {
		GameObject objetoChocado = Tablero.tablero[x][y];
		if (objetoChocado != null) {// si choqué contra algo
			if (objetoChocado instanceof Fruta) {
				viboritaQueSeMueve.crecer();
				((Fruta) objetoChocado).setItem();
			} else if (objetoChocado instanceof Cuerpo) {
				// Si el cuerpo avanza en la misma direccion no debería morir
				viboritaQueSeMueve.morir();
			} else if (objetoChocado instanceof Snake) {
				viboritaQueSeMueve.morir();
				Snake viboritaALaQueChocan = (Snake) objetoChocado;
				if (viboritaQueSeMueve.getDireccion() == -viboritaALaQueChocan.getDireccion())
					viboritaALaQueChocan.morir();
			}
		}

	}


	/**
	 * 
	 * @param snake
	 * @param snake2
	 * @return hubo colision
	 */
	public static boolean colisionaCabeza(Snake snake, Snake snake2) {
		// Determinar de donde vienen
		int x1 = snake.getPosX(), y1 = snake.getPosY(), x2 = snake2.getPosX(), y2 = snake2.getPosY();
		// Verificar para que lado van
		int dir1 = snake.getDireccion();
		if (snake.getDireccion() % 2 == 0) { // si se mueve para el ESTE/OESTE
			// if(x1+dir1%2>Medida.ANCHO/Medida.SIZE-1)
			// x1=
		}
		// matar o no matar
		return false;

	}

	/**
	 * Verifica las colisiones de la snake y las predicciones
	 * 
	 * @param s
	 * @return objeto colisionado, si no null
	 */
	public static GameObject colisionTablero(Snake s) {
		int x = s.getPosX(), y = s.getPosY(), dir = s.getDireccion();
		GameObject colisionado = null;
		if (dir == 0)// si no me muevo no puedo colisionar ¯\_(ºuº)_/¯
			return null;
		if (Math.abs(dir % 2) == 1) {// si voy vertical
			if (y + dir < 0)// me fijo justo delante
				y = Medida.LARGO / Medida.SIZE - 1;
			else if (y + dir > Medida.LARGO / Medida.SIZE - 1)
				y = 0;
			else
				y += dir;
			colisionado = Tablero.tablero[x][y];// en el siguiente espacio hay
			if (colisionado != null) {// si hay algo
				return colisionado;
			}
			// si no tengo nada justo delante
			if (x + 1 > Medida.ANCHO / Medida.SIZE - 1)// me fijo al ESTE
				x = 0;
			else
				x += 1;
			colisionado = Tablero.tablero[x][y];
			if (colisionado != null && colisionado instanceof Snake && // si hay una serpiente y
					((Snake) colisionado).getDireccion() == Medida.OESTE) {// se mueve hacia mi bloque
				return colisionado;
			}
			// si no tengo una serpiente del Este
			if (x - 1 < 0) // me fijo al OESTE
				x = Medida.ANCHO / Medida.SIZE - 2;
			else if (x - 2 < 0)
				x = Medida.ANCHO / Medida.SIZE - 1;
			else
				x -= 2;
			colisionado = Tablero.tablero[x][y];
			if (colisionado != null && colisionado instanceof Snake && // si hay una serpiente y
					((Snake) colisionado).getDireccion() == Medida.ESTE) {// se mueve hacia mi bloque
				return colisionado;
			}
			// si no tengo una serpiente del OESTE
			if (y + dir < 0)// me fijo enfrente
				y = Medida.LARGO / Medida.SIZE - 1;
			else if (y + dir > Medida.LARGO / Medida.SIZE - 1)
				y = 0;
			else
				y += dir;
			if (x + 1 > Medida.ANCHO / Medida.SIZE - 1)
				x = 0;
			else
				x += 1;
			colisionado = Tablero.tablero[x][y];
			if (colisionado != null && colisionado instanceof Snake && // Si hay una serpiente y
					((Snake) colisionado).getDireccion() == -dir) {// se mueve hacia mi bloque
				return colisionado;
			}
		} else { // si voy horizontal
			if (x + (dir / 2) > Medida.ANCHO / Medida.SIZE - 1)// me fijo justo delante
				x = 0;
			else if (x + (dir / 2) < 0)
				x = Medida.ANCHO / Medida.SIZE - 1;
			else
				x += (dir / 2);
			colisionado = Tablero.tablero[x][y];// en el siguiente espacio hay
			if (colisionado != null) {// si hay algo
				return colisionado;
			}
			// si no tengo nada justo delante
			if (y + 1 > Medida.LARGO / Medida.SIZE - 1)// me fijo al SUR
				y = 0;
			else
				y += 1;
			colisionado = Tablero.tablero[x][y];
			if (colisionado != null && colisionado instanceof Snake && // si hay una serpiente y
					((Snake) colisionado).getDireccion() == Medida.NORTE) {// se mueve hacia mi bloque
				return colisionado;
			}
			// si no tengo una serpiente del SUR
			if (y - 1 < 0) // me fijo al NORTE
				y = Medida.LARGO / Medida.SIZE - 2;
			else if (y - 2 < 0)
				y = Medida.LARGO / Medida.SIZE - 1;
			else
				y -= 2;
			colisionado = Tablero.tablero[x][y];
			if (colisionado != null && colisionado instanceof Snake && // si hay una serpiente y
					((Snake) colisionado).getDireccion() == Medida.SUR) {// se mueve hacia mi bloque
				return colisionado;
			}
			// si no tengo una serpiente del NORTE *a mi lateral
			if (x + (dir / 2) < 0)// me fijo enfrente
				x = Medida.ANCHO / Medida.SIZE - 1;
			else if (x + (dir / 2) > Medida.ANCHO / Medida.SIZE - 1)
				x = 0;
			else
				x += (dir / 2);
			if (y + 1 > Medida.LARGO / Medida.SIZE - 1)
				y = 0;
			else
				y += 1;
			colisionado = Tablero.tablero[x][y];
			if (colisionado != null && colisionado instanceof Snake && // Si hay una serpiente y
					((Snake) colisionado).getDireccion() == -dir) {// se mueve hacia mi bloque
				return colisionado;
			}
		}
		return null;
	}

}
