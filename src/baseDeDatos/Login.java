package baseDeDatos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.hibernate.cfg.annotations.ListBinder;

import cliente.Conexion;
import cliente.Mensaje;
import medida.Parametro;
import ui.Lobby;
import ui.Multiplayer;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends JFrame {

	JTextField textField1, textField2;
	JLabel label1, label2;
	JButton boton1;

	public Login() {

		this.setTitle("Login");

		setLayout(null);

		textField1 = new JTextField();
		textField2 = new JPasswordField();

		label1 = new JLabel("Correo electronico");
		label2 = new JLabel("Contraseña");

		label1.setBounds(50, 50, 120, 30);
		label2.setBounds(210, 50, 120, 30);

		textField1.setBounds(50, 80, 120, 25);
		textField2.setBounds(200, 80, 120, 25);

		boton1 = new JButton("Iniciar sesion");
		boton1.setBounds(350, 75, 120, 30);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Multiplayer multiplayer = new Multiplayer();
				setVisible(false);
			}
		});

		boton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Socket cliente;
				Persona persona = new Persona();
				persona.setMail(textField1.getText().trim());
				persona.setContraseña(textField2.getText().trim());

				Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

				Matcher verificador = pattern.matcher(persona.getMail());

				if (verificador.find() == false)
					JOptionPane.showMessageDialog(null,
							"el email ingresado es invalido.verifique su email e ingreselo nuevamente");
				else if (textField1.getText().equals("") || textField2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "llene todos los campos");
				}
				 else {
					Conexion conexion = new Conexion();
					Persona resp = new Persona();
					resp= conexion.loguear(new Mensaje(Parametro.LOGGEO, persona));
					if(resp.getNick()!=null) {
						
						setVisible(false);
						Lobby lob = new Lobby(resp);
						lob.setVisible(true);
						
					}else {
						JOptionPane.showMessageDialog(null,"email o contraseña incorrecta , intentelo nuevamente", "error", JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		});

		setBounds(0, 0, 500, 350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		add(label1);
		add(label2);
		add(textField1);
		add(textField2);
		add(boton1);
		setVisible(true);
	}

}
