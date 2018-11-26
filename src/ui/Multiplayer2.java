package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JEditorPane;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.jgoodies.forms.layout.Sizes;

import base.DAO;
import base.DAOCliente;
import baseDeDatos.Persona;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Pattern;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Multiplayer2 extends JFrame {

	private JPanel contentPane;
	private JLabel lblTitle;
	private JLabel lblCorreo;
	private JLabel lblPassword;
	private JTextField txtPassword;
	private JTextField txtCorreo;
	private JButton btnLogIn;
	private JButton btnRegister;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Multiplayer2 frame = new Multiplayer2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Multiplayer2() {
		//Ventana
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Bienvenida frame = new Bienvenida();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 480);
		//ContentPane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{56, 193, 193, 52, 139, 0};
		gbl_contentPane.rowHeights = new int[] {91, 30, 31, 35, 40, 75, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		//componentes
		lblTitle = new JLabel("Multijugador");
		lblCorreo = new JLabel("Ingrese su correo");
		lblPassword = new JLabel("Ingrese su contrase\u00F1a");
		btnLogIn = new JButton("Iniciar Sesi\u00F3n");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtCorreo.getText().isEmpty()||txtPassword.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Ingrese el correo y la contraseña", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Persona persona = new Persona();
				persona.setMail(txtCorreo.getText().trim());
				persona.setContraseña(txtPassword.getText().trim());
				DAO dao = new DAOCliente();
				Persona res = dao.realizarLogin(persona);
				if(res != null) {
					JOptionPane.showMessageDialog(null, "Ingreso exitoso", "Exito", JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
					dispose();
					Lobby lobby = new Lobby(res);
					lobby.setVisible(true);
					//cierro la ventana Multiplayer
					return;
				}
				JOptionPane.showMessageDialog(null, "El correo o la contraseña son erróneos", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnRegister = new JButton("Registrarse");
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		txtCorreo = new JTextField();
		txtCorreo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!txtCorreo.getText().isEmpty() &&!Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", txtCorreo.getText())) {
					txtCorreo.setText("");
					JOptionPane.showMessageDialog(null, "El correo ingresado no es válido", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		txtPassword = new JPasswordField();
		//no permito espacios en la contraseña
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == ' ')
					e.consume();
			}
		});
		
		lblTitle.setFocusable(false);
		lblTitle.setFont(new Font("Andale Mono", 1, 20));

		lblCorreo.setFocusable(false);
		
		lblPassword.setFocusable(false);
		lblPassword.setLabelFor(txtPassword);
		
		txtCorreo.setColumns(10);
		lblCorreo.setLabelFor(txtCorreo);
		
		txtPassword.setColumns(10);
		
		//Constraints
		//lblTitulo
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridwidth = 4;
		gbc_lblTitle.fill = GridBagConstraints.VERTICAL;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 1;
		gbc_lblTitle.gridy = 0;
		//lblCorreo
		GridBagConstraints gbc_lblCorreo = new GridBagConstraints();
		gbc_lblCorreo.fill = GridBagConstraints.BOTH;
		gbc_lblCorreo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCorreo.gridx = 1;
		gbc_lblCorreo.gridy = 2;
		//lblPass
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.fill = GridBagConstraints.BOTH;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 2;
		gbc_lblPassword.gridy = 2;
		//txtCorreo
		GridBagConstraints gbc_txtCorreo = new GridBagConstraints();
		gbc_txtCorreo.fill = GridBagConstraints.BOTH;
		gbc_txtCorreo.insets = new Insets(0, 0, 5, 5);
		gbc_txtCorreo.gridx = 1;
		gbc_txtCorreo.gridy = 3;
		//txtPassword
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.fill = GridBagConstraints.BOTH;
		gbc_txtPassword.insets = new Insets(0, 0, 5, 5);
		gbc_txtPassword.gridx = 2;
		gbc_txtPassword.gridy = 3;
		//btnLogIn
		GridBagConstraints gbc_btnLogIn = new GridBagConstraints();
		gbc_btnLogIn.gridheight = 2;
		gbc_btnLogIn.fill = GridBagConstraints.BOTH;
		gbc_btnLogIn.insets = new Insets(0, 0, 5, 0);
		gbc_btnLogIn.gridx = 4;
		gbc_btnLogIn.gridy = 3;
		//btnRegister
		GridBagConstraints gbc_btnRegister = new GridBagConstraints();
		gbc_btnRegister.fill = GridBagConstraints.BOTH;
		gbc_btnRegister.gridx = 4;
		gbc_btnRegister.gridy = 5;
		//adds
		contentPane.add(lblTitle, gbc_lblTitle);
		contentPane.add(lblCorreo, gbc_lblCorreo);
		contentPane.add(lblPassword, gbc_lblPassword);
		contentPane.add(txtCorreo, gbc_txtCorreo);
		contentPane.add(txtPassword, gbc_txtPassword);
		contentPane.add(btnLogIn, gbc_btnLogIn);
		contentPane.add(btnRegister, gbc_btnRegister);
	}

}
