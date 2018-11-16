package lobby;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import base.Sala;
import baseDeDatos.Persona;

public class Lobby extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton crearSala;
	private JTable tablaDeSalas;
	private JButton accesoRapido, salir, unirse;
	private JPanel panel1,panel2;
	private JScrollPane scroll ;
	
	
	public Lobby(Persona persona) {
		

		super("-- Lobby --");
		String[] encabezado = { "#", "Titulo del Juego", "Players" };
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 500, 510);
		this.setLocationRelativeTo(null);

		this.setLayout(new BorderLayout());

		crearSala = new JButton("Crear Sala");
		accesoRapido = new JButton("Unirse Rapido");
		salir = new JButton("Salir");
		unirse = new JButton("Unirse");
		
		
		panel1 = new JPanel();
		panel2 = new JPanel();
		
		
		
		//creo un modelo de tabla por defaul, que recibe la data que contiene, encabezado.
		DefaultTableModel modeloTabla = new DefaultTableModel(new Object[][] {}, encabezado);
		
		//contruyo la tabla con el modelo.
		tablaDeSalas = new JTable(modeloTabla);
		
		//ancho de la columna
		tablaDeSalas.getColumnModel().getColumn(0).setPreferredWidth(30);
		tablaDeSalas.getColumnModel().getColumn(1).setPreferredWidth(200);
		tablaDeSalas.getColumnModel().getColumn(2).setPreferredWidth(80);
		
		setBackground(Color.GRAY);
		
		//agregar dato a la tabla
		DefaultTableModel model = (DefaultTableModel) tablaDeSalas.getModel();
		
		
		
			SalaEspera c = new SalaEspera("LA MEJOR SALA ",persona);
			
			model.addRow(c.getList());

		
		//barra para ver los datos
		scroll = new JScrollPane(tablaDeSalas);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		unirse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int filaSeleccionada =  tablaDeSalas.getSelectedRow();
				
				if(filaSeleccionada == -1)
					return;
				
				System.out.println(filaSeleccionada);
				
			
				//pensar
						
				
			}
		});
		
		
		crearSala.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				SalaEspera sala= new SalaEspera("OTRA SALA",null);
				DefaultTableModel model = (DefaultTableModel) tablaDeSalas.getModel();
				model.addRow(sala.getList());
				
				if(sala.getCantJugadores() == 0) {
					model.removeRow(tablaDeSalas.getRowCount()-1);
				}
				model.fireTableDataChanged();
					
				
			}
		});
		
		
		panel1.add(unirse);
		panel1.add(accesoRapido);
		panel1.add(salir);
		panel1.add(crearSala);
		add(panel1, BorderLayout.NORTH);
		add(panel2);
		add(scroll);
		setVisible(true);
	
		
	}
	

	

}
