package base;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import GameObjects.Fruta;
import GameObjects.Snake;
import TecladoEvento.InputTeclado;
import medida.Medida;

public class Sala extends JFrame {

	private int ANCHO = Medida.ANCHO;
	private int LARGO = Medida.LARGO;
//	private int BORDE = Medida.BORDE;

	/**
	 * el ultimo parametro es el Id de la snake, lo hardcodie porque el idgeneral de
	 * GameObject no incrementaba, como son diferentes, puedo saber cuando se chocan
	 * snake
	 */
	
	InputTeclado InputTeclado = new InputTeclado();
	public static ArrayList<Snake> viboritas = new ArrayList<Snake>();

	Escenario laminaJuego;

	public Sala() {
		super("Game");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, ANCHO, LARGO);
		this.setLocationRelativeTo(null);

		laminaJuego = new Escenario();

		Puntaje laminaPuntaje = new Puntaje();

		add(laminaJuego);
		add(laminaPuntaje);

		
		/*
		 * seTFocusable para que maneje los inputs dentro del panel
		 * */
		this.addKeyListener(InputTeclado);
		setFocusable(true);

		setVisible(true);
		setResizable(false);
	}

	public void start() {

		while (true) {
			/*
			 * Actualizar llama a move del Escenario, y repaint.
			 * 
			 * */
			
			laminaJuego.actualizar(InputTeclado.direccion);	
			try {
				Thread.sleep(100); // HAGO QUE LOS PROCESOS SE EJECUTEN CADA 100 MILISEGUNDOS
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	class Puntaje extends JPanel {
	
		/*
		 * PANEL DE LA VENTANA DE PUNTAJE
		 * FALTA TERMINAR, EN CLASE APARTE
		 * 
		 * */
		private JLabel puntaje;

		public Puntaje() {

			puntaje = new JLabel("PUNTAJE");

			add(puntaje);
			setBounds(0, 0, ANCHO/4, LARGO);
			setBackground(Color.RED);

		}
	}
}
