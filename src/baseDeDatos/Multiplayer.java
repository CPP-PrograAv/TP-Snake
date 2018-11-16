package baseDeDatos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Multiplayer extends JFrame {

	private JButton boton1, boton2;

	public Multiplayer() {

		this.setTitle("Multiplayer");
		setLayout(null);
		boton1 = new JButton("Iniciar sesion");
		boton2 = new JButton("Registrarse");

		setBounds(0, 0, 500, 500);
		boton1.setBounds(175, 200, 110, 30);
		boton2.setBounds(175, 250, 110, 30);
		add(boton1);
		add(boton2);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Bienvenida bienvenida = new Bienvenida();
				setVisible(false);
			}
		});
		boton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				setVisible(false);
			}
		});

		boton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Registro registro = new Registro();
				setVisible(false);

			}
		});

	}

	public static void main(String[] args) {

		Multiplayer mp = new Multiplayer();
	}
}
