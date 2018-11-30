package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import base.Juego;
import base.Jugador;
import baseDeDatos.Persona;
import cliente.Conexion;
import cliente.Mensaje;
import medida.Parametro;

public class Lobby extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton crearSala;
	private JTable tablaDeSalas;
	private JButton unirse;
	private JPanel panel1, panel2;
	private JScrollPane scroll;
	private ArrayList<SalaEspera> vSalas = new ArrayList<SalaEspera>();
	private DefaultTableModel model;

	public Lobby(Persona persona) {
		super("-- Lobby --");
		String[] encabezado = { "#", "Titulo del Juego", "Players" };
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 500, 510);
		this.setLocationRelativeTo(null);

		this.setLayout(new BorderLayout());

		crearSala = new JButton("Crear Sala");
		unirse = new JButton("Unirse");

		panel1 = new JPanel();
		panel2 = new JPanel();

		// creo un modelo de tabla por defaul, que recibe la data que contiene,
		// encabezado.
		DefaultTableModel modeloTabla = new DefaultTableModel(new Object[][] {}, encabezado);

		// contruyo la tabla con el modelo.
		tablaDeSalas = new JTable(modeloTabla);

		// ancho de la columna
		tablaDeSalas.getColumnModel().getColumn(0).setPreferredWidth(30);
		tablaDeSalas.getColumnModel().getColumn(1).setPreferredWidth(200);
		tablaDeSalas.getColumnModel().getColumn(2).setPreferredWidth(80);

		setBackground(Color.GRAY);

		// barra para ver los datos
		scroll = new JScrollPane(tablaDeSalas);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		// Actualiza Lobby
		Conexion conexion = new Conexion();
		vSalas = conexion.actualizarLobby(new Mensaje(Parametro.ACTUALIZAR_LOBBY));
		
		model = (DefaultTableModel) tablaDeSalas.getModel();

		for (SalaEspera s : vSalas)
			model.addRow(s.getList());
		model.fireTableDataChanged();

		unirse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int filaSeleccionada = tablaDeSalas.getSelectedRow();
				Conexion conexion = new Conexion();
				(conexion.UnirseSala(new Mensaje(Parametro.UNIRSE,persona,filaSeleccionada))).setVisible(true);

			}
		});

		crearSala.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

//				String nombreSala = "";
//				nombreSala = JOptionPane.showInputDialog(null, "Ingrese el nombre de la sala");

				Sala salaIntermedia = new Sala();
				salaIntermedia.setVisible(true);
				
				salaIntermedia.getButton().addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						salaIntermedia.setVisible(false);
						Conexion conexion = new Conexion();
						SalaEspera sala = conexion.crearSala(new Mensaje(Parametro.NUEVASALA, persona, salaIntermedia.getNombre()));
						sala.setDatos(salaIntermedia.getTipoJuego(), salaIntermedia.getModoFruta());
						sala.setVisible(true);
						
						model = (DefaultTableModel) tablaDeSalas.getModel();
						model.addRow(sala.getList());
						model.fireTableDataChanged();
						
					}
				});
				
				
			}
		});

		panel1.add(unirse);
		panel1.add(crearSala);
		add(panel1, BorderLayout.NORTH);
		add(panel2);
		add(scroll);
		setVisible(true);

	}

	public ArrayList<SalaEspera> getvSalas() {
		return vSalas;
	}

	public void setvSalas(ArrayList<SalaEspera> vSalas) {
		this.vSalas = vSalas;
	}

}
