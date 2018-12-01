package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;

import GameObjects.Obstaculo;
import base.Juego;
import baseDeDatos.Persona;

public class SalaEspera extends JFrame{

	static int numSala=0;
	private String titulo;
	private int cantJugadores = 0;
	private Object[] lista;
	private JButton iniciar,salir;
	private JComboBox mapa, tipoDeJuego;
	private String tipoMapa, juegoSeleccionado;
	
	
	public SalaEspera(String nombreSala,Persona persona) {
		
		
		super("Juego Espera");
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 500, 500);
		//setLocationRelativeTo(null);
		
		this.titulo = nombreSala;
		numSala++;
		this.cantJugadores++;
		
		iniciar = new JButton("Iniciar");
		iniciar.setBounds(50, 100, 100, 30);
		add(iniciar);
		//add(iniciar, BorderLayout.NORTH);
		mapa = new JComboBox(); 
		mapa.setModel(new DefaultComboBoxModel(new String [] {"Escalera", "LetraT", "Cruz"} ));
		mapa.setBounds(50, 270, 120, 30);
		add(mapa);
		
		mapa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tipoMapa = (String)mapa.getSelectedItem();
			}
		});
		
		tipoDeJuego = new JComboBox(); 
		tipoDeJuego.setModel(new DefaultComboBoxModel(new String [] {"Supervivencia", "Tiempo", "Ambas"} ));
		tipoDeJuego.setBounds(50, 400, 120, 30);
		add(tipoDeJuego);
		
		tipoDeJuego.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				juegoSeleccionado = (String)tipoDeJuego.getSelectedItem();
			}
		});
		
		iniciar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Juego juego = new Juego(persona, tipoMapa, juegoSeleccionado);
				new Thread(juego).start();
			}
		});
		
		this.setVisible(true);
		salir = new JButton("Salir");
		salir.setBounds(50, 350, 100, 30);
		add(salir);
		//add(salir, BorderLayout.SOUTH);
		
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
		lista[1] =(this.titulo);
		lista[2] = (this.cantJugadores);
		return lista;
	}

	
	

	
	
	
	
	
}
