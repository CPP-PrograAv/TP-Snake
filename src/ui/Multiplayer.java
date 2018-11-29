package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import baseDeDatos.Login;
import baseDeDatos.Registro;

public class Multiplayer extends JFrame {

	private JButton btnLogIn, btnRegister;
	JTextField txtMail, txtPassword;
	JLabel lblCorreo, lblPassword;
	public Multiplayer() {

		this.setTitle("Multiplayer");
		setLayout(null);
		btnLogIn = new JButton("Iniciar sesion");
		btnRegister = new JButton("Registrarse");

		setBounds(0, 0, 500, 500);
		btnLogIn.setBounds(175, 200, 110, 30);
		btnRegister.setBounds(175, 250, 110, 30);
		add(btnLogIn);
		add(btnRegister);
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
		btnLogIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				setVisible(false);
			}
		});

		btnRegister.addActionListener(new ActionListener() {

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
