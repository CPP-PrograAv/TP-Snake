package ventanaCliente;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;
import javax.swing.*;

import base.*;
import TecladoEvento.*;
import baseDeDatos.*;
import medida.*;

/**

	SACAR EXIT PARA QUE NO ROMPA
	
*/
public class VentanaJuego extends JFrame {

	private int ANCHO = Medida.ANCHO;
	
	/**
	 * el ultimo parametro es el Id de la snake, lo hardcodie porque el idgeneral de
	 * GameObject no incrementaba, como son diferentes, puedo saber cuando se chocan
	 * snake
	 */

	private JPanel contentPane, panelpuntaje;
	private VentanaTablero laminaJuego;
	private JList<String> lista;
	private JScrollPane scroll;
	private Jugador persona;
	

	
	public static void main(String[] args) {
		VentanaJuego v = new VentanaJuego(null);
		v.setVisible(true);
	}

	public VentanaJuego(Jugador persona) {
		super("Game");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, Medida.ANCHO_VENTANA, Medida.LARGO_VENTANA);
		this.setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(null);
		this.setContentPane(contentPane);

		laminaJuego = new VentanaTablero();
		laminaJuego.setBounds(ANCHO / 4, 0, Medida.ANCHO_VENTANA, Medida.LARGO_VENTANA);
		contentPane.add(laminaJuego);

		JLabel titulo = new JLabel("SCORE");
		titulo.setBounds(40, 5, 120, 40);
		contentPane.add(titulo);

		JLabel nombre = new JLabel("Nombre");
		nombre.setBounds(5, 30, 100, 40);
		contentPane.add(nombre);

		JLabel puntaje = new JLabel("Puntos");
		puntaje.setBounds(65, 30, 100, 40);
		contentPane.add(puntaje);

		panelpuntaje = new JPanel();
		panelpuntaje.setBounds(5, 70, 120, 300);

		lista = new JList<>();
		lista.setBackground(Color.YELLOW);
		panelpuntaje.add(lista);

		scroll = new JScrollPane();
		scroll.setViewportView(lista);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panelpuntaje.add(scroll);

		contentPane.add(panelpuntaje);
		// seTFocusable para que maneje los inputs dentro del panel

	}



}