package baseDeDatos;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.hibernate.cfg.annotations.ListBinder;

import cliente.Cliente;
import cliente.Conexion;
import cliente.Mensaje;
import medida.Parametro;
import ui.Bienvenida;
import ui.Lobby;
import ui.Multiplayer;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends JFrame {

	JTextField textField1, textPassword;
	JLabel labelEmail, labelContra;
	JButton botonIniciarSesion;
	JButton botonCancelar;
	JPanel contentPane;
	
	public static void main(String[] args) {
		Login lg = new Login();
	}

	public Login() {

		this.setTitle("Login");
		
		setLayout(null);
		setBounds(0, 0, 500, 350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelMensaje = new JLabel("Inicio de Sesion");
		labelMensaje.setBounds(50,20,180,50);
		labelMensaje.setForeground(Color.BLUE);
		labelMensaje.setFont(new Font("Serif", Font.PLAIN, 25));
		contentPane.add(labelMensaje);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(210, 100, 120, 25);
		contentPane.add(textPassword);

		labelContra = new JLabel("Contraseña");
		labelContra.setBounds(210, 70, 120, 30);
		contentPane.add(labelContra);
		
		labelEmail = new JLabel("Correo electronico");
		labelEmail.setBounds(50, 70, 120, 30);
		contentPane.add(labelEmail);

		textField1 = new JTextField();
		textField1.setBounds(50, 100, 120, 25);
		contentPane.add(textField1);

		botonCancelar = new JButton("Cancelar");
		botonCancelar.setBounds(50, 160, 120, 30);
		contentPane.add(botonCancelar);
		
		botonIniciarSesion = new JButton("Iniciar sesion");
		botonIniciarSesion.setBounds(210, 160, 120, 30);
		contentPane.add(botonIniciarSesion);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Multiplayer multiplayer = new Multiplayer();
				setVisible(false);
			}
		});
		
		botonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Multiplayer multiplayer = new Multiplayer();
			
			}
		});

		botonIniciarSesion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Socket cliente;
				Persona persona = new Persona();
				persona.setMail(textField1.getText().trim());
				persona.setContraseña(textPassword.getText().trim());

				Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

				Matcher verificador = pattern.matcher(persona.getMail());
				if (verificador.find() == false)
					JOptionPane.showMessageDialog(null,
							"el email ingresado es invalido.verifique su email e ingreselo nuevamente");
				else if (textField1.getText().equals("") || textPassword.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "llene todos los campos");
				}
				 else {

					Persona resp = new Persona();
					resp= Cliente.getConexion().loguear(new Mensaje(Parametro.LOGGEO, persona));
					if(resp.getNick()!=null) {
						setVisible(false);
						Bienvenida bienvenida = new Bienvenida(resp);						
					}else {
						JOptionPane.showMessageDialog(null,"Email o contraseña incorrecta , intentelo nuevamente", "error", JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		});
		
		setVisible(true);
	}

}
