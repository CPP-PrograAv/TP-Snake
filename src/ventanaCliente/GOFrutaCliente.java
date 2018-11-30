package ventanaCliente;

import java.awt.Color;
import java.awt.Graphics2D;

import medida.Medida;

public class GOFrutaCliente extends GObjetoCliente{

	@Override
	public void paint(Graphics2D g2d) {
		g2d.setColor(Color.GREEN); // la fruta siempre sera verde
		int padding = Medida.BORDE / 2;
		g2d.fillRect(this.getPosX() * Medida.SIZE + 1 + padding, this.getPosY() * Medida.SIZE + 1 + padding,
				Medida.SIZE - 2, Medida.SIZE - 2);

	}
	
}
