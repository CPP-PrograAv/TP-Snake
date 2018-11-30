package ventanaCliente;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import GameObjects.*;
import cliente.Conexion;
import medida.Medida;

public class VentanaTablero extends JPanel {

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
	public VentanaTablero() {
		setBackground(Color.WHITE);
		pintar();
	}
	
	public void setTablero(GameObject tablero[][]) {
		this.tablero = tablero;
	}

	public void pintar() {
		repaint();
	}
	
	
	
	public void paint(Graphics g) {
		super.paint(g); // VUELVO A PINTAR, Y BORRO EL ANTERIOR
		Graphics2D g2d = (Graphics2D) g;

		for (int i = 0; i < Medida.SIZE_MATRIZ; i++) {
			for (int j = 0; j <Medida.SIZE_MATRIZ; j++)
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
	

}