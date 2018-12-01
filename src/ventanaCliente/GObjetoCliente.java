package ventanaCliente;

import java.awt.*;
import java.io.Serializable;

import base.*;
import medida.Medida;

public abstract class GObjetoCliente {

	private Punto ubicacion;
	private int size;
	
	public void paint(Graphics2D g2d) {
		int padding = Medida.BORDE / 2;
		g2d.fillRect(getPosX() * size + 1 + padding, getPosY() * size + 1 + padding, size - 2, size - 2);
	}

	public int getPosX() {
		return this.ubicacion.getX();
	}


	public int getPosY() {
		return this.ubicacion.getY();
	}



}