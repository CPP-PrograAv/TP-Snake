	package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import base.Juego;
import baseDeDatos.Persona;

public class Bienvenida extends JFrame {

	JButton btnSinglePlayer, btnMultiPlayer;
	JLabel lblTitulo;

	public Bienvenida() {

		setLayout(null);
		btnSinglePlayer = new JButton("Single Player");
		btnMultiPlayer = new JButton("Multiplayer");
		lblTitulo = new JLabel("Slither.IO");
		this.setTitle("Bienvenida");

		btnSinglePlayer.setBounds(170, 150, 120, 30);
		btnMultiPlayer.setBounds(170, 250, 120, 30);
		lblTitulo.setBounds(190, 50, 120, 50);

		lblTitulo.setFont(new Font("Andale Mono", 1, 20));
		lblTitulo.setForeground(Color.BLACK);

		setBounds(0, 0, 500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		setVisible(true);

		add(btnSinglePlayer);
		add(btnMultiPlayer);
		add(lblTitulo);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				confirmarCierreVentana();
			}
		});
		
		btnSinglePlayer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Juego juego = new Juego(new Persona(1,"Jugador","Azul"));
				new Thread(juego).start();
			}
		});
		
		btnMultiPlayer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Multiplayer multiplayer = new Multiplayer();
				setVisible(false);
			}
		});

	}

	private void confirmarCierreVentana() {
		int respuesta = JOptionPane.showConfirmDialog(this, "Desea cerrar la aplicacion", "confirmar Salir",
				JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	
}
