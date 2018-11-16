package base;

import java.awt.Color;
import java.util.Comparator;

import GameObjects.Snake;
import medida.Medida;

public class Jugador implements Comparable<Jugador> {

	public static int size = Medida.SIZE;
	private static int idGenral = 0;
	private int puntaje;
	private String nombreJugador;

	Snake snake;

	public Jugador(String nombreJugador) {
		snake = new Snake(80 / size, 80 / size, ++idGenral, Color.RED);
		this.nombreJugador = nombreJugador;
	}

	// settear un jugador.
	public Jugador(String nombreJugador, int x, int y) {
		snake = new Snake(x / size, y / size, ++idGenral, Color.BLUE);
		this.nombreJugador = nombreJugador;

	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public Snake getSnake() {
		return snake;
	}

	public void setSnake(Snake snake) {
		this.snake = snake;
	}

	public String getNombre() {
		return this.nombreJugador;
	}

	@Override
	public int compareTo(Jugador arg0) {
		return arg0.getSnake().getPuntaje() - this.getSnake().getPuntaje();
	}

}
