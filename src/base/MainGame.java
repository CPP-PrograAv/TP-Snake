package base;

import javax.swing.JFrame;

import TecladoEvento.InputTeclado;

public class MainGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//probar clase Inputteclado
//		JFrame jsd = new JFrame("hola");
//		jsd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		jsd.setVisible(true);
//		jsd.addKeyListener(new InputTeclado());
//		
		new Escenario().start();
	}

}
