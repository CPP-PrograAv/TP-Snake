package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.COUNT;

import base.Juego;
import base.Jugador;
import baseDeDatos.Persona;
import cliente.Conexion;

public class SalaEspera extends JFrame {
	private static int cont = 0;
	private JPanel contentPane;
	private int numSala;
	private int cantJugadores;
	private Object[] lista;
	private JButton iniciar, salir;
	private String nombreSala;
	private ArrayList<Jugador> Vjugadores = new ArrayList<Jugador>();
	private JList<String> nickJugadores;

	public SalaEspera(String nombreSala, Persona persona) {

		super(nombreSala);
		this.nombreSala = nombreSala;

		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel players = new JLabel("Jugadores: ");
		players.setBounds(20, 10, 100, 20);
		contentPane.add(players);

		nickJugadores = new JList<>();
		nickJugadores.setBounds(20, 40, 150, 200);
		nickJugadores.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.add(nickJugadores);

		JLabel adm = new JLabel(persona.getNick());
		adm.setBounds(20, 20, 80, 40);
		nickJugadores.add(adm);

		iniciar = new JButton("Iniciar");
		iniciar.setBounds(250, 300, 80, 30);
		contentPane.add(iniciar, BorderLayout.NORTH);

		salir = new JButton("Salir");
		salir.setBounds(160, 300, 80, 30);
		contentPane.add(salir, BorderLayout.SOUTH);

		Jugador jugador = new Jugador(persona.getNick());
		Vjugadores.add(jugador);

		this.numSala = ++cont;
		this.cantJugadores++;

		iniciar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Juego juego = new Juego(persona);
				new Thread(juego).start();
			}
		});

		salir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				cantJugadores--;
			}
		});

	}

	public void setDatos(String tipoMJ, String tipoMFruta) {

		JLabel mensaje = new JLabel("Tipo de Juego:");
		mensaje.setBounds(200,40, 200,60);
		contentPane.add(mensaje);
		
		JLabel mj = new JLabel(tipoMJ);
		mj.setBounds(200,60, 200,60);
		contentPane.add(mj);
		
		JLabel mensaje2 = new JLabel("Cantidad de Fruta:");
		mensaje2.setBounds(200,90, 200,60);
		contentPane.add(mensaje2);
		
		JLabel tf = new JLabel(tipoMFruta);
		tf.setBounds(200,110, 200,60);
		contentPane.add(tf);
		
	}

	public int getCantJugadores() {
		return this.cantJugadores;
	}

	public void añadirJugador(Persona persona) {
		Vjugadores.add(new Jugador(persona.getNick()));

		JLabel n = new JLabel(persona.getNick());
		n.setBounds(20, 50, 80, 20);
		nickJugadores.add(n);

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
