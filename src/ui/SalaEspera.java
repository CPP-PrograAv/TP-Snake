package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.COUNT;

import base.Juego;
import base.Jugador;
import baseDeDatos.Persona;

public class SalaEspera extends JFrame {
	private static int cont=0; 
	private  int numSala;
	private int cantJugadores;
	private Object[] lista;
	private JButton iniciar, salir;
	private String nombreSala;
	private ArrayList<Jugador> Vjugadores = new ArrayList<Jugador>();
	private JLabel nombreJugador;
	
	public SalaEspera(String nombreSala, Persona persona) {

		
		super(nombreSala);
		Jugador jugador = new Jugador(persona.getNick());
		Vjugadores.add(jugador);
		this.nombreSala = nombreSala;
		
		nombreJugador = new JLabel(persona.getNick());
		

		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		setLocationRelativeTo(null);

		this.numSala = ++cont;
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
		
		add(nombreJugador,BorderLayout.CENTER);
	}

	protected void cerrarVentana() {
		this.setVisible(false);
		cantJugadores--;

	}

	public int getCantJugadores() {
		return this.cantJugadores;
	}

	public void añadirJugador(Persona persona) {
		Vjugadores.add(new Jugador(persona.getNick()));
	}
	public Object[] getList() {

		lista = new Object[4];
		lista[0] = (this.numSala);
		lista[1] = (this.nombreSala);
		lista[2] = (this.cantJugadores);
		return lista;
	}

	public String getNombreSala() {
		return nombreSala;
	}


}
