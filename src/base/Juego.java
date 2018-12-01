package base;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.*;
import javax.swing.*;

import GameObjects.Snake;
import TecladoEvento.InputTeclado;
import baseDeDatos.Persona;
import medida.Medida;
import servidor.EscuchaCliente;

/**
 * SACAR EXIT PARA QUE NO ROMPA
 */
public class Juego extends Thread implements Serializable {
	private static int id = 0;
	private int ANCHO = Medida.ANCHO;
	private ArrayList<Jugador> jugadores = new ArrayList<>();
	private JPanel contentPane, panelpuntaje;
	private Tablero laminaJuego;
	private JList<String> lista;
	private JScrollPane scroll;
	private boolean enJuego = true;

	public Juego(Jugador persona) {
		super("Game");
		construirVentana();
		// AGREGAR JUGADORES
		añadirJugador();
		añadirJugadorBot(120, 300);
	}

	public Juego() {
		super("Game");
		construirVentana();
	}

	public void agregarJugador(Jugador jugador) {
		jugador.setSnake(new Snake(2, 2, id++, Color.BLUE, laminaJuego));
		jugadores.add(jugador);
	}

	private void añadirJugador() {
		Jugador JugadorRobot = new Jugador("BOT", 0);
		jugadores.add(JugadorRobot);
		laminaJuego.agregarSnake(jugadores.get(jugadores.size() - 1).getSnake());
	}

	private void añadirJugadorBot(int x, int y) {
		Jugador JugadorDefecto = new Jugador("BOT", x, y, laminaJuego);
		jugadores.add(JugadorDefecto);
		laminaJuego.agregarSnake(jugadores.get(jugadores.size() - 1).getSnake());
	}

	@Override
	public void run() {
		laminaJuego.setSnakes(jugadores);
		long nextGameTick = System.currentTimeMillis();
		long sleepTime = 0;

		while (enJuego) {
			laminaJuego.actualizar();
			lista.setModel(modelList());

			// FPS
			nextGameTick += Medida.SKIP_TICKS;
			sleepTime = nextGameTick - System.currentTimeMillis();
			if (sleepTime >= 0) {
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.getMessage();
				}
			}
		}
	}

	private DefaultListModel<String> modelList() {

		DefaultListModel<String> model = new DefaultListModel<>();
		// agregar un metodo que ordene tanto el puntaje de mayor a menor, y paralelo
		// los jugadores.
		Collections.sort(jugadores);

		for (int i = 0; i < jugadores.size(); i++) {
			String cadena = jugadores.get(i).getNombre() + " " + jugadores.get(i).getSnake().getPuntaje();
			model.addElement(alinearCadenas(cadena));
		}
		return model;
	}

	private String alinearCadenas(String cadena) {

		String cadenita = cadena.trim(), res;
		String[] cadenas = cadenita.split(" ");
		int nume = Integer.parseInt(cadenas[1]), cantidadCaracteres = 15, cantidadNumeros = 5;
		int cantidad = cadenas[0].length();

		if (cadenas[0].length() > cantidadCaracteres) {
			cantidad = cantidadNumeros;
		}

		cadenas[0] = cadenas[0].substring(0, cantidad);
		res = String.format("%1$-10s", cadenas[0]);
		res = String.format("%1$-" + cantidadCaracteres + "s", cadenas[0]);
		res += String.format("%0" + cantidadNumeros + "d", nume);
		return res;
	}

	private void construirVentana() {

		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(null);

		laminaJuego = new Tablero();
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

	public Tablero getTablero() {
		return laminaJuego;
	}

	public JList<String> getJList() {
		return lista;
	}

	public boolean getEnJuego() {
		return enJuego;
	}

	public synchronized void move(String nick, int dir) {
		for (int i = 0; i < jugadores.size(); i++) {
			if (jugadores.get(i).getNombre().equals(nick)) {
				jugadores.get(i).getSnake().setDireccion(dir);
			}
		}
	}
}
