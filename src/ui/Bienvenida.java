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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import base.Juego;
import baseDeDatos.Login;
import baseDeDatos.Persona;

public class Bienvenida extends JFrame {
	
	JButton btnSinglePlayer, btnMultiPlayer;
	JLabel lblTitulo;
	JPanel contentPane;
	
	public Bienvenida(Persona user) {

		this.setTitle("Bienvenida");
		setLayout(null);
		setBounds(0, 0, 500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitulo = new JLabel("Slither.IO");
		lblTitulo.setBounds(190, 10, 150, 40);
		lblTitulo.setFont(new Font("Andale Mono", 1, 20));
		lblTitulo.setForeground(Color.BLACK);
		contentPane.add(lblTitulo);

		JLabel bienvenida = new JLabel("Bienvenido: "+ user.getNick());
		bienvenida.setForeground(Color.BLACK);
		bienvenida.setBounds(20, 20,200, 100);
		contentPane.add(bienvenida);
		
		JLabel puntaje = new JLabel("Su puntaje actual: ");
		puntaje.setBounds(20,50,200,100);
		contentPane.add(puntaje);
		
		JLabel muertes = new JLabel("Cantidad de muertes: ");
		muertes.setBounds(20,80,200,100);
		contentPane.add(muertes);
		
		JLabel victorias = new JLabel("Cantidad de victorias: ");
		victorias.setBounds(20,110,200,100);
		contentPane.add(victorias);
		
		btnMultiPlayer = new JButton("Multiplayer");
		btnMultiPlayer.setBounds(260, 190, 120, 30);
		contentPane.add(btnMultiPlayer);

		btnSinglePlayer = new JButton("Single Player");
		btnSinglePlayer.setBounds(90, 190, 120, 30);
		contentPane.add(btnSinglePlayer);
		
		setVisible(true);


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
				
				Lobby lobby = new Lobby(user);
				setVisible(false);
			}
		});

	}

	private void confirmarCierreVentana() {
//		int respuesta = JOptionPane.showConfirmDialog(this, "Desea cerrar la aplicacion", "confirmar Salir",
//				JOptionPane.YES_NO_OPTION);
//		if (respuesta == JOptionPane.YES_OPTION) {
			System.exit(0);
//		}
	}

	
}
