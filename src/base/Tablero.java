package base;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import GameObjects.*;
import medida.Medida;

public class Tablero extends JPanel {

	private static final long serialVersionUID = 1L;

	// hace falta tomar los valores si ya estan en Medida?
	private int ANCHO = Medida.ANCHO;
	private int LARGO = Medida.LARGO;
	private int BORDE = Medida.BORDE;

	// Variables para controlar el juego
	public static GameObject tablero[][] = new GameObject[Medida.SIZE_MATRIZ][Medida.SIZE_MATRIZ];
	public static ArrayList<Snake> viboritas = new ArrayList<Snake>();
	public static int matriz[][] = new int[Medida.ANCHO / Medida.SIZE][Medida.LARGO / Medida.SIZE];
	public static int size = Medida.SIZE;

	public static int cantidadFruta = 4; // Manejo la cantidad que estara siempre en el tablero

	public static ArrayList<Obstaculo> obstaculos = new ArrayList<Obstaculo>();
	// private Obstaculo obs = new Obstaculo();

	/**
	 * Crea un escenario vacío
	 */
	public Tablero(String obs) {
		setBackground(Color.WHITE);
		agregarObstaculo(obs);
		agregarFruta();
		
	}

	/**
	 * Agrego los obstaculos del tablero
	 */
	public void agregarObstaculo(String obs) {
		if(obs == "Escalera") {
			int horizontal = 1;
			int desplazamiento = 10;
			for (int i = 1; i < 4; i++) {
				Punto p1 = new Punto(5 + desplazamiento *i,5 + desplazamiento*i);
				obstaculos.add(new Obstaculo(obs, horizontal , p1));
			}
			horizontal++;//vertical
			for (int i = 1; i < 4; i++) {
				Punto p1 = new Punto(15 + desplazamiento*i ,5 + desplazamiento * i);
				obstaculos.add(new Obstaculo(obs, horizontal,p1 ));
			}
		}
		if(obs == "LetraT") {
			
			int desplazamiento = 5;
			for (int i = 1; i < 4; i++) {
				Punto p1 = new Punto(10 + desplazamiento*i, 20);
				obstaculos.add(new Obstaculo(obs, 1 , p1));
			}
			for (int i = 1; i < 4; i++) {
				Punto p1 = new Punto(23 ,15 + desplazamiento*i);
				obstaculos.add(new Obstaculo(obs, 2,p1 ));
			}
		}	
			
		if(obs == "Cruz") {
			
			int desplazamiento = 10;
			for (int i = 1; i < 4; i++) {
				Punto p1 = new Punto(5 + desplazamiento*i, 25);
				obstaculos.add(new Obstaculo(obs, 1 , p1));
			}
			for (int i = 1; i < 4; i++) {
				Punto p1 = new Punto(25 ,2 + desplazamiento*i);
				obstaculos.add(new Obstaculo(obs, 2,p1 ));
			}
		}
	}

	/**
	 * Agrego las frutas al tablero basandome en cantidadFruta que es el limite de
	 * frutas en el tablero
	 */

	private void agregarFruta() {
		for (int i = 0; i < cantidadFruta; i++)
			new Fruta();
		System.out.println(Fruta.cantidad);
	}

	public void actualizar(int direccion) {

		// snake.setDireccion(direccion);
		viboritas.get(0).setDireccion(direccion);
		verificarColisiones();
		move();
		repaint();

		// sacarlo afuera
		if (viboritas.get(0).muerto) {
			System.out.println("Muerto" + viboritas.get(0).getIdSnake());

			// mensaje
			JOptionPane.showMessageDialog(null, "termino el juego, su vibora esta muerta", "fin del juego",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}

	private void verificarColisiones() {
		for (int i = 0; i < viboritas.size(); i++) {
			GameObject colisionado = Colision.colisionTablero(viboritas.get(i));
			if (colisionado != null)
				colisionado.accionColision(viboritas.get(i));

			// sacar del array los muertos
			if (i > 0 && viboritas.get(i).getMuerto() == true) {
				System.out.println("Muerto: " + viboritas.get(i).getIdSnake());
				viboritas.remove(i);
//				i--;
			}
		}

		// sacar del array a los muertos, no manejo el 0, pensar otra forma..
//		for (int i = 1; i < viboritas.size(); i++) {
//			if(viboritas.get(i).getMuerto() == true) {
//				System.out.println("Muerto: " +viboritas.get(i).getIdSnake());
//				viboritas.remove(i);
//			}
//		}

	}

	private void move() {
		// muevo las serpientes
		for (Snake s : viboritas) {
			s.move();
		}
	}

	/**
	 * Pinto lo que se encuentre en el tablero si es distinto de null.
	 * Tanto el obstaculo, la fruta y el cuerpo tienen un color constante, no hace falta
	 * preguntar que objeto se encuentra en esa posicion del tablero (evito usar los
	 * instanceOf)
	 * 
	 * Para el pintado de la cabeza de la snake, se cambio el constructor de la
	 * snake para que reciba como parametro el color (que podriamos tomarlo en el
	 * registro, aunque podriamos tirar algo random tambien...)
	 * 
	 */

	public void paint(Graphics g) {
		super.paint(g); // VUELVO A PINTAR, Y BORRO EL ANTERIOR
		Graphics2D g2d = (Graphics2D) g;

		for (int i = 0; i < ANCHO / size; i++) {
			for (int j = 0; j < ANCHO / size; j++)
				if (tablero[j][i] != null) { // si es distinto de null pinto.
					tablero[j][i].paint(g2d);
				}
		}

		// Pinto los bordes
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(0, 0, ANCHO + BORDE, BORDE / 2);// borde superior
		g2d.fillRect(ANCHO + BORDE / 2, 0, BORDE / 2, LARGO + BORDE); // borde derecho
		g2d.fillRect(0, 0, BORDE / 2, LARGO + BORDE); // borde izquierdo
		g2d.fillRect(0, LARGO + BORDE / 2, ANCHO + BORDE, BORDE / 2);// borde inferior
	}

	/**
	 * Agrego una snake al tablero
	 * 
	 * @param snake
	 */
	public void agregarSnake(Snake snake) {
		viboritas.add(snake);
	}

	/**
	 * Muestro la matriz de gameObjects
	 */
	private void mostrarTablero() {
		System.out.println("ini:");
		for (int i = 0; i < ANCHO / size; i++) {
			for (int j = 0; j < ANCHO / size; j++)
				System.out.print(tablero[j][i] + "\t");
			System.out.println();
		}
		System.out.println("fin");
	}

}
