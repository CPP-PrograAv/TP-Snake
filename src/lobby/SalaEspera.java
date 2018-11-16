package lobby;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;

import base.Sala;
import baseDeDatos.Persona;

public class SalaEspera extends JFrame{

	static int numSala=0;
	private String titulo;
	private int cantJugadores = 0;
	private Object[] lista;
	private JButton iniciar,salir;
	
	
	public SalaEspera(String nombreSala,Persona persona) {
		
		
		super("Sala Espera");
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
				Sala sala = new Sala(persona);
				new Thread(sala).start();
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
		lista[1] =(this.titulo);
		lista[2] = (this.cantJugadores);
		return lista;
	}

	
	

	
	
	
	
	
}
