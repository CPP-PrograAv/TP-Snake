package baseDeDatos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

import cliente.*;
import medida.*;
import ui.Multiplayer;

public class Registro extends JFrame {

	JTextField textField1, textField2, textField3, textField4;
	JButton boton1;
	JLabel label1, label2, label3, label4;
	JComboBox colores;

	public Registro() {
		Persona persona = new Persona();
		this.setTitle("Registro");

		setLayout(null);
		setBounds(0, 0, 500, 500);

		label1 = new JLabel("Correo electronico");
		label1.setBounds(50, 25, 120, 30);

		label2 = new JLabel("Contraseña");
		label2.setBounds(50, 95, 100, 30);

		label3 = new JLabel("Nick");
		label3.setBounds(50, 165, 60, 30);

		label4 = new JLabel("Color");
		label4.setBounds(50, 240, 60, 30);

		textField1 = new JTextField();
		textField1.setBounds(50, 55, 120, 30);

		textField2 = new JPasswordField();
		textField2.setBounds(50, 125, 120, 30);

		textField3 = new JTextField();
		textField3.setBounds(50, 195, 120, 30);

		colores = new JComboBox();
		colores.setModel(new DefaultComboBoxModel(new String[] { "Rojo", "Azul", "Verde" }));
		colores.setBounds(50, 270, 120, 30);
		boton1 = new JButton("Registrarte");
		boton1.setBounds(70, 325, 120, 30);

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

				Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

				persona.setMail(textField1.getText().trim());
				persona.setContraseña(textField2.getText().trim());
				persona.setNick(textField3.getText().trim());
				persona.setColor(colores.getSelectedItem().toString().trim());

				Matcher verificador = pattern.matcher(persona.getMail());

				if (verificador.find() == false)
					JOptionPane.showMessageDialog(null,
							"el email ingresado es invalido.verifique su email e ingreselo nuevamente");
				else {
					if (textField1.getText().equals("") || textField2.getText().equals("")
							|| textField3.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
					} else {
						Conexion conexion = new Conexion();
						System.out.println("ENTRO");
						int resp = conexion.registrarse(new Mensaje(Parametro.REGISTRARSE, persona));

						if (resp == Parametro.DUPLICADO)
							JOptionPane.showMessageDialog(null, "El correo ya esta registrado.. Ingrese nuevamente");
						else {
							JOptionPane.showMessageDialog(null, "Se genero el registro con éxito!");
							Login login = new Login();
							setVisible(false);
						}
					}
				}
			}
		});
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		add(label1);
		add(label2);
		add(label3);
		add(label4);

		add(textField1);
		add(textField2);
		add(textField3);
		add(colores);

		add(boton1);

		setVisible(true);

	}

	public static void main(String[] args) {

		Registro reg = new Registro();
	}
}
