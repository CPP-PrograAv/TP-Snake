package ventanaCliente;

import java.awt.Color;
import java.awt.Graphics2D;

import medida.Medida;

public class GOObstCliente extends GObjetoCliente{

	@Override
	public void paint(Graphics2D g2d) {
		g2d.setColor(Color.ORANGE);//el obstaculo siempre sera naranja
		int padding = Medida.BORDE / 2;
		g2d.fillRect(this.getPosX() * Medida.SIZE + 1 + padding, this.getPosY() * Medida.SIZE + 1 + padding,
				Medida.SIZE, Medida.SIZE);		
	}
	
}
