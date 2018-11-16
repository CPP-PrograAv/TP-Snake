	package baseDeDatos;

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
import javax.swing.JTextField;

import base.Sala;



public class Bienvenida extends JFrame {

	JButton boton1, boton2;
	JLabel label1;

	public Bienvenida() {

		setLayout(null);
		boton1 = new JButton("single player");
		boton2 = new JButton("multiplayer");
		label1 = new JLabel("Slither.IO");
		this.setTitle("Bienvenida");

		boton1.setBounds(170, 150, 120, 30);
		boton2.setBounds(170, 250, 120, 30);
		label1.setBounds(190, 50, 120, 50);

		label1.setFont(new Font("Andale Mono", 1, 20));
		label1.setForeground(Color.BLACK);

		setBounds(0, 0, 500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setVisible(true);

		add(boton1);
		add(boton2);
		add(label1);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				confirmarCierreVentana();
			}
		});
		
		boton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				Sala sala = new Sala(null);
				new Thread(sala).start();
			
				
				
				
			}
		});
		boton2.addActionListener(new ActionListener() {

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

	public static void main(String[] args) {
		Bienvenida ventanaLoggin = new Bienvenida();
		
	}

}
