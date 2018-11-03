package lobby;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import base.Sala;

public class Lobby extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton crearSala;
	private JTable tablaDeSalas;
	private JButton accesoRapido, salir, unirse;

	private ArrayList<Object> salas;
	
	public Lobby() {
		super("-- Lobby --");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 500, 510);
		this.setLocationRelativeTo(null);

		this.setLayout(new BorderLayout());

		PanelA panelA = new PanelA();
		PanelB panelB = new PanelB();
		
		this.add(panelA, BorderLayout.NORTH);
		this.add(panelB);
		
		salas = new ArrayList<>();
		
		
		
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
				
				SalaEspera sala= new SalaEspera("OTRA SALA");
				DefaultTableModel model = (DefaultTableModel) tablaDeSalas.getModel();
				model.addRow(sala.getList());
				
				if(sala.getCantJugadores() == 0) {
					model.removeRow(tablaDeSalas.getRowCount()-1);
				}
				model.fireTableDataChanged();
					
				
			}
		});
		
		
		
		
		this.setVisible(true);
		
		
		
		
	}
	
	class PanelA extends JPanel {

		public PanelA() {
			
			crearSala = new JButton("Crear Sala");
			accesoRapido = new JButton("Unirse Rapido");
			salir = new JButton("Salir");
			unirse = new JButton("Unirse");
			
	
			add(unirse);
			add(accesoRapido);
			add(salir);
			add(crearSala);

		}

	}
	
	class PanelB extends JPanel {

		String[] encabezado = { "#", "Titulo del Juego", "Players" };

		public PanelB() {
			
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
			
			
			
				SalaEspera c = new SalaEspera("LA MEJOR SALA ");
				
				model.addRow(c.getList());

			
			
			
			//barra para ver los datos
			JScrollPane scroll = new JScrollPane(tablaDeSalas);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			add(scroll);

		}

	}



	public static void main(String[] arg) {
		new Lobby();
		
	}

}
