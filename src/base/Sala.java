package base;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;
import javax.swing.border.Border;

import GameObjects.Snake;
import TecladoEvento.InputTeclado;
import base.Sala.Puntaje;
import medida.Medida;

public class Sala extends JFrame implements Runnable{

	private int ANCHO = Medida.ANCHO;
	private int LARGO = Medida.LARGO;
	
	/**
	 * el ultimo parametro es el Id de la snake, lo hardcodie porque el idgeneral de
	 * GameObject no incrementaba, como son diferentes, puedo saber cuando se chocan
	 * snake
	 */
	
	InputTeclado InputTeclado = new InputTeclado();	
	ArrayList<Jugador> jugadores = new ArrayList<>();
	JPanel contentPane;
	Tablero laminaJuego;
	JList<String> lista;
	
	public Sala() {
		super("Game");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, Medida.ANCHO_VENTANA, Medida.LARGO_VENTANA);
		this.setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(null);
		this.setContentPane(contentPane);
		
		laminaJuego = new Tablero();
		laminaJuego.setBounds(ANCHO/4, 0, Medida.ANCHO_VENTANA, Medida.LARGO_VENTANA);
		contentPane.add(laminaJuego);
		
		JLabel titulo = new JLabel("SCORE");
		titulo.setBounds(40, 5, 120, 40);
		contentPane.add(titulo);
		
		JLabel nombre = new JLabel("Nombre");
		nombre.setBounds(5,30, 100,40);
		contentPane.add(nombre);
		
		JLabel puntaje = new JLabel("Puntos");
		puntaje.setBounds(70,30, 100, 40);
		contentPane.add(puntaje);
		
//		JList<String> lista = new JList<>();
//		lista.setBounds(5,70, 100,30);
//		lista.setBackground(Color.BLUE);
//		contentPane.add(lista);
		
		Puntaje panelPuntaje = new Puntaje();
		panelPuntaje.setBounds(5,70, 120,300);
		contentPane.add(panelPuntaje);
		
		//seTFocusable para que maneje los inputs dentro del panel 
		this.addKeyListener(InputTeclado);
		setFocusable(true);
		setVisible(true);
		setResizable(false);
		
		//AGREGAR JUGADORES
		añadirJugador();
		añadirJugadorBot(120,300);
	}
	
	private void añadirJugador() {
		Jugador JugadorRobot= new Jugador("C++");
		jugadores.add(JugadorRobot);
		laminaJuego.agregarSnake(jugadores.get(jugadores.size()-1).getSnake());
	}
	
	private void añadirJugadorBot(int x, int y) {
		Jugador JugadorDefecto= new Jugador("BOT",x,y);
		jugadores.add(JugadorDefecto);
		laminaJuego.agregarSnake(jugadores.get(jugadores.size()-1).getSnake());
	}
	
	/**
	 * Inicia la partida
	 */
	
	

	@Override
	public void run() {
		//AGREGAR SNAKE, CON FOR EACH JUGADOR AGREGANDOLE LA SNAKE
				//FPS
				long nextGameTick = System.currentTimeMillis();
				long sleepTime = 0;
				
				while (true) {
					/*
					 * Actualizar llama a move del Escenario, y repaint. 
					 * */
					laminaJuego.actualizar(InputTeclado.direccion);
					lista.setModel(modelList());
					//FPS
					nextGameTick += Medida.SKIP_TICKS;
					sleepTime = nextGameTick- System.currentTimeMillis();
					
					if(sleepTime>=0) {
						
						try {
							Thread.sleep(sleepTime);
						} catch (InterruptedException e) {
							e.getMessage();
							System.out.println("ERROR SLEEP");
						}
					} else
						System.out.println("NO LLEGUE AL FPS");
				}
			}
			private DefaultListModel<String> modelList() {
					
					DefaultListModel<String> model = new DefaultListModel<>();
					//agregar un metodo que ordene tanto el puntaje de mayor a menor, y paralelo los jugadores.
				Collections.sort(jugadores);
				for(int i=0;i<jugadores.size(); i++) {
					//model.addElement(      calcularString(jugadores.get(i).getNombre() , puntaje.get(i))   ); // normalizar cadena
					
					String cadena = jugadores.get(i).getNombre() +" "+ jugadores.get(i).getSnake().getPuntaje();
					//model.addElement(jugadores.get(i).getNombre() + " "+ jugadores.get(i).getSnake().getPuntaje()+"   ");
					model.addElement(alinearCadenas(cadena));
				}
				return model;
			}
			class Puntaje extends JPanel {
				/*
				 * PANEL DE LA VENTANA DE PUNTAJE
				 * FALTA TERMINAR, EN CLASE APARTE
				 * 
				 * */
				public Puntaje() {			
					lista = new JList<>();
					lista.setBackground(Color.YELLOW);
					add(lista);
					
					JScrollPane scroll = new JScrollPane();
					scroll.setViewportView(lista);
					scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					add(scroll);
				}
			}
			
			public String alinearCadenas(String cadena) {
				
				String cadenita=cadena.trim(),res;
				String[]cadenas=cadenita.split(" ");
				int nume=Integer.parseInt(cadenas[1]),cantidadCaracteres=15,cantidadNumeros=5;
				int cantidad=cadenas[0].length();
				
				
				if(cadenas[0].length()>cantidadCaracteres) {
					cantidad=cantidadNumeros;
				}
				
				cadenas[0]=cadenas[0].substring(0, cantidad);
				res=String.format("%1$-10s",cadenas[0]);
				res=String.format("%1$-"+cantidadCaracteres+"s", cadenas[0]);
				res+=String.format("%0"+cantidadNumeros+"d", nume);
				return res;	
			}
}
	
	
