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
	private JButton  unirse;
	private JPanel panel1, panel2;
	private JScrollPane scroll;
	private ArrayList<SalaEspera> vSalas;
	boolean bandera=false;
	DefaultTableModel model;
	
	public Lobby(Persona persona) {
		super("-- Lobby --");
		String[] encabezado = { "#", "Titulo del Juego", "Players" };
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 500, 510);
		this.setLocationRelativeTo(null);

		this.setLayout(new BorderLayout());

		if(!bandera) {
			vSalas = new ArrayList<SalaEspera>();
			bandera=true;
		}else {
			vSalas=this.getvSalas();
			for(SalaEspera i : vSalas)
				model.addRow(i.getList());
		}
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

		unirse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int filaSeleccionada = tablaDeSalas.getSelectedRow();
/*
				if (filaSeleccionada == -1)
					return;*/
				SalaEspera salaEspera = null;
				Jugador jugador = new Jugador(persona.getNick());
				salaEspera.setvJugadorSala(jugador,filaSeleccionada);

				

			}
		});

		crearSala.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean bandera=false;
				
				String nombreSala = "";
				nombreSala = JOptionPane.showInputDialog(null, "Ingrese el nombre de la sala");
				
				Sala salaIntermedia = new Sala();
				Conexion conexion = new Conexion();
				bandera=conexion.crearSala(new Mensaje(Parametro.NUEVASALA, persona,nombreSala));
				
				if(bandera)
					System.out.println("se creo correctamente");
				else
					System.out.println("fallo");
			 //   model = (DefaultTableModel) tablaDeSalas.getModel();
				//model.addRow(sala.getList());
				
				
				/*if (sala.getCantJugadores() == 0) {
					model.removeRow(tablaDeSalas.getRowCount() - 1);
				}
				model.fireTableDataChanged();
*/
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
