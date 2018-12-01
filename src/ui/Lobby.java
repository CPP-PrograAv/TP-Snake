package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import base.Juego;
import base.Jugador;
import baseDeDatos.Persona;
import cliente.Cliente;
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
	private HashMap<Integer, SalaEspera> hSalas = new HashMap<>();
	private DefaultTableModel model;
	private JButton actualizarSalas;

	public Lobby(Persona persona) {
		super("-- Lobby --");
		String[] encabezado = { "#", "Titulo del Juego", "Players" };
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 500, 510);
		this.setLocationRelativeTo(null);

		this.setLayout(new BorderLayout());

		crearSala = new JButton("Crear Sala");
		unirse = new JButton("Unirse");
		actualizarSalas = new JButton("actualizar");

		panel1 = new JPanel();
		panel2 = new JPanel();

		panel1.add(unirse);
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

		actualizarSalas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				hSalas = Cliente.getConexion().actualizarLobby(new Mensaje(Parametro.ACTUALIZAR_LOBBY));
				model = (DefaultTableModel) tablaDeSalas.getModel();

				for (Integer key : hSalas.keySet())
					model.addRow(hSalas.get(key).getList());
				model.fireTableDataChanged();
			}
		});

		panel1.add(crearSala);
		panel1.add(actualizarSalas);
		add(panel1, BorderLayout.NORTH);
		add(panel2);
		add(scroll);
		setVisible(true);

		unirse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = tablaDeSalas.getSelectedRow();
				int numeroSala = (int) model.getValueAt(filaSeleccionada, 0);

				if (filaSeleccionada == -1)
					JOptionPane.showMessageDialog(null, "Debe seleccionar una sala...");
				else {
					Conexion con = Cliente.getConexion();
					SalaEspera sala = con.UnirseSala(new Mensaje(Parametro.UNIRSE, persona, numeroSala));
					SalaEsperaHilo hilo = new SalaEsperaHilo(con, sala);
					hilo.start();
					sala.setVisible(true);
				}

			}
		});

		crearSala.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nombreSala = "";
				nombreSala = JOptionPane.showInputDialog(null, "Ingrese el nombre de la sala");

				SalaEspera sala = new SalaEspera(nombreSala, persona,
						(int) (Cliente.getConexion().obtenerIndiceSala()));
				Cliente.getConexion().crearSala(new Mensaje(Parametro.NUEVASALA, sala, persona));
				SalaEsperaHilo hilo = new SalaEsperaHilo(Cliente.getConexion(), sala);
				hilo.start();

				model = (DefaultTableModel) tablaDeSalas.getModel();
				model.addRow(sala.getList());
				model.fireTableDataChanged();

			}
		});

	}
}