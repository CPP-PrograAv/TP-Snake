package base;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	
	private Fruta item = new Fruta();

	/**
	 * Crea un escenario vacío
	 */
	public Tablero() {
			setBackground(Color.WHITE);
	}

	public void actualizar(int direccion) {

		// snake.setDireccion(direccion);
		
		viboritas.get(0).setDireccion(direccion);
		verificarColisiones();
		move();
		paint(this.getGraphics());
		

		//sacarlo afuera
		if (viboritas.get(0).muerto) {
			System.out.println("Muerto" + viboritas.get(0).getIdSnake() );
			if (viboritas.get(1).muerto)
				System.out.println("Muerto" + viboritas.get(1).getIdSnake());
			
			// mensaje
			JOptionPane.showMessageDialog(null, "termino el juego, su vibora esta muerta", "fin del juego",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}

	private void verificarColisiones() {
		for (int i=0; i<viboritas.size(); i++) {
			GameObject colisionado = Colision.colisionTablero(viboritas.get(i));
			if (colisionado instanceof Fruta) {// si es una fruta
				
				viboritas.get(i).crecer();
//				Sala.puntaje.set(i,viboritas.get(i).getPuntaje());
				item.setItem();
				
			} else if (colisionado instanceof Cuerpo) {// si es un cuerpo
				viboritas.get(i).morir();
			} else if (colisionado instanceof Snake) {// si es otra snake
				if (Colision.colisionaCabeza(viboritas.get(i), (Snake) colisionado)) {
					((Snake) colisionado).morir();
				}
				viboritas.get(i).morir();
			}
		}
	}

	private void move() {
		// muevo las serpientes
		for (Snake s : viboritas) {
			s.move();
		}
	}

	public void paint(Graphics g) {
		super.paint(g); // VUELVO A PINTAR, Y BORRO EL ANTERIOR
		Graphics2D g2d = (Graphics2D) g;
		// g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		// RenderingHints.VALUE_ANTIALIAS_ON); //mejora el dibujo en el tema de los
		// bordes

		for (int i = 0; i < ANCHO / size; i++) {
			for (int j = 0; j < ANCHO / size; j++)
				if (tablero[j][i] != null) { // y preguntar para cambiar color
					if (tablero[j][i] == viboritas.get(0)) { // la snake podría tener su color en su instancia para no preguntar,
													// que sea su responsabilidad su color
						g2d.setColor(Color.RED);
					} else if (tablero[j][i] == viboritas.get(1)) {
						g2d.setColor(Color.BLUE);
					} else if (tablero[j][i] == item) {
						g2d.setColor(Color.GREEN);
					} else if (tablero[j][i] instanceof Cuerpo) {
						g2d.setColor(Color.BLACK);
					} else {
						g2d.setColor(Color.MAGENTA);// Magenta si no está
					}
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
		// creo la lista de objetoa a agregar
		ArrayList<GameObject> agregado = new ArrayList<>();
		agregado.add(snake);
		agregado.addAll(snake.getCuerpos());
		// los agrego al tablero
		/* No haría falta porque cuando se crean se crean en la matriz de GameObjects */
		for (GameObject gObj : agregado)
			tablero[gObj.getPosX()][gObj.getPosY()] = gObj;// el buen polimorfismo
		// agrego la referencia en la lista de snakes
		
		viboritas.add(snake);
	}

	/**
	 * Muestra la matriz de IDs
	 */
	private void mostrarmatriz() {
		System.out.println("ini:");
		for (int i = 0; i < ANCHO / size; i++) {
			for (int j = 0; j < ANCHO / size; j++)
				System.out.print(matriz[j][i] + " ");
			System.out.println("\n");
		}
		System.out.println("fin");
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
