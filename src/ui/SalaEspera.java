package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;

import base.Juego;
import base.Jugador;
import baseDeDatos.Persona;

public class SalaEspera extends JFrame {

	static int numSala;
	private String titulo;
	private int cantJugadores;
	private Object[] lista;
	private JButton iniciar, salir;
	private String nombreSala;
	private ArrayList<Jugador> vJugadorSala = new ArrayList<Jugador>();

	public SalaEspera(String nombreSala, Persona persona) {

		super(nombreSala);
		Jugador jugador = new Jugador(persona.getNick());
		this.nombreSala = nombreSala;
		vJugadorSala.add(jugador);

		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		setLocationRelativeTo(null);

		this.titulo = nombreSala;
		numSala++;
		this.cantJugadores++;

		iniciar = new JButton("Iniciar");
		add(iniciar, BorderLayout.NORTH);

		iniciar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Juego juego = new Juego(persona);
				new Thread(juego).start();
			}
		});

		this.setVisible(true);
		salir = new JButton("Salir");
		add(salir, BorderLayout.SOUTH);

		salir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cerrarVentana();
			}
		});

	}

	protected void cerrarVentana() {
		this.setVisible(false);
		cantJugadores--;

	}

	public int getCantJugadores() {
		return this.cantJugadores;
	}

	public Object[] getList() {

		lista = new Object[4];
		lista[0] = (this.numSala);
		lista[1] = (this.titulo);
		lista[2] = (this.cantJugadores);
		return lista;
	}

	public String getNombreSala() {
		return nombreSala;
	}

	public ArrayList<Jugador> getvJugadorSala() {
		return vJugadorSala;
	}

	public void setvJugadorSala(Jugador nuevo, int indice) {
		this.vJugadorSala.add(indice, nuevo);
	}

}
