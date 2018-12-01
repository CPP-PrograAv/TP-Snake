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
import javax.swing.event.ListDataListener;
import javax.swing.table.DefaultTableModel;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.COUNT;

import base.Juego;
import base.Jugador;
import baseDeDatos.Persona;
import cliente.Cliente;
import cliente.Conexion;
import cliente.Mensaje;
import medida.Parametro;

public class SalaEspera extends JFrame {
	private JPanel contentPane;
	private int numSala;
	private int cantJugadores;
	private Object[] lista;
	private JButton iniciar, salir;
	private String nombreSala;
	private ArrayList<Jugador> Vjugadores = new ArrayList<Jugador>();
	private Conexion conexion;
	private DefaultTableModel model;


	public SalaEspera(String nombreSala, Persona persona,int numSala) {

		super(nombreSala);
		this.nombreSala = nombreSala;
		
		String[] encabezado = { "Nick" };
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

		model = new DefaultTableModel(new Object[][] {}, encabezado);
		
		JLabel mensaje = new JLabel("Tipo de Juego:");
		mensaje.setBounds(200, 40, 200, 60);
		contentPane.add(mensaje);

//		JLabel mj = new JLabel(tipoJuego);
//		mj.setBounds(200,60, 200,60);
//		contentPane.add(mj);

		JLabel mensaje2 = new JLabel("Cantidad de Fruta:");
		mensaje2.setBounds(200, 90, 200, 60);
		contentPane.add(mensaje2);

//		JLabel tf = new JLabel(tipoModoFruta);
//		tf.setBounds(200,110, 200,60);
//		contentPane.add(tf);

		iniciar = new JButton("Iniciar");
		iniciar.setBounds(250, 300, 80, 30);
		contentPane.add(iniciar);

		salir = new JButton("Salir");
		salir.setBounds(160, 300, 80, 30);
		contentPane.add(salir);

		Jugador jugador = new Jugador(persona.getNick(), persona.getPuntaje());
		Vjugadores.add(jugador);

		this.numSala = numSala;
		this.cantJugadores++;

		iniciar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Mensaje msj = new Mensaje(Parametro.EMPEZAR_JUEGO);
				Conexion c = Cliente.getConexion();
				if (c != null) {
					c.empezarJuego(msj);
					setVisible(false);
				} else
					System.out.println("No puedo comenzar..");
			}

		});

		salir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Mensaje msj = new Mensaje(Parametro.SALIO_JUGADOR, persona);
				Conexion c = Cliente.getConexion();
				if (c != null)
					c.solicitarSalir(msj);
				else
					System.out.println("NO HAY NADA");

				dispose();
			}
		});

		setVisible(true);
	}

	public void setConexion(Conexion con) {
		this.conexion = con;

		if (this.conexion != null)
			System.out.println("HAY CONEXIOOOOOOOOOOOOOOON");
		else
			System.out.println("NO HAY CONEXIOOOON");
	}

	public Conexion getConexion() {
		return this.conexion;
	}

	public int getCantJugadores() {
		return this.cantJugadores;
	}

	public void agregarJugador(Persona persona) {
		Vjugadores.add(new Jugador(persona.getNick(), persona.getPuntaje()));
//		String[] nuevo = new String[0];
//		nuevo[0] = persona.getNick();
//		model.addRow(nuevo);
	}

	public void sacarJugador(Persona persona) {
		Vjugadores.remove(new Jugador(persona.getNick(), persona.getPuntaje()));
		cantJugadores--;
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

	public int getNumSala() {
		return numSala;
	}

	public void setNumSala(int numSala) {
		this.numSala = numSala;
	}
	

}