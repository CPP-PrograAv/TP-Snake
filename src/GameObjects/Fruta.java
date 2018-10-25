package GameObjects;

import java.awt.*;
import base.*;
import medida.*;

public class Fruta extends GameObject {


	
	public static int IdItem = -1;

	public Fruta() {
		
		super(new Punto ( (int)(Math.random()*(Medida.ANCHO/Medida.SIZE - 1)),
						(int)(Math.random()*(Medida.LARGO/Medida.SIZE - 1)) ), IdItem);
				
		System.out.println("Fruta en X: "+getPosX()+" e Y: "+getPosY());
	}

	public void setItem(int dx, int dy) {
		setPosition(dx, dy, IdItem);
	}

	public void setItem() {
		
		Escenario.matriz[getPosX()][getPosY()] = 0;
		// Acá se debería verificar que no se superponga a la snake o a un powerup
		int x = (int) (Math.random() * (Medida.ANCHO / Medida.SIZE - 1));
		int y = (int) (Math.random() * (Medida.LARGO / Medida.SIZE - 1));
		setPosition(x, y, IdItem);
		System.out.println("Fruta en X: " + getPosX() + " e Y: " + getPosY());
	}


	@Override
	public void paint(Graphics2D g2d) {
		g2d.setColor(Color.GREEN);
		int padding = Medida.BORDE/2;
		g2d.fillRect(this.getPosX() * Medida.SIZE + 1 + padding, 
				this.getPosY() * Medida.SIZE + 1 + padding, Medida.SIZE-2,
				Medida.SIZE-2);

	}

}
