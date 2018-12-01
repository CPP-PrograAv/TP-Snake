package ventanaCliente;

import java.awt.Color;
import java.awt.Graphics2D;

import GameObjects.Cuerpo;
import medida.Medida;

public class GOSnakeCliente extends GObjetoCliente{
	
	
	
	@Override
	public void paint(Graphics2D g2d) {
		g2d.setColor(Color.MAGENTA);
		int size = Medida.SIZE;
		int padding = Medida.BORDE / 2;
		g2d.fillRect(getPosX() * size + 1 + padding, getPosY() * size + 1 + padding, size - 2, size - 2);
	}
	
}
