package GameObjects;

import java.awt.*;
import base.*;
import medida.*;

public class Fruta extends GameObject {


	
	public static int IdItem = -1;

	public Fruta() {
		
		super(ubicar(), IdItem);
				
		System.out.println("Fruta en X: "+getPosX()+" e Y: "+getPosY());
	}

	public void setItem(int dx, int dy) {
		setPosition(dx, dy, IdItem);
	}

	public void setItem() {
		
		Escenario.matriz[getPosX()][getPosY()] = 0;
		// Acá se debería verificar que no se superponga a la snake o a un powerup
		setPosition(ubicar(), IdItem);
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
	/**
	 * Genera un punto al azar donde la matriz del escenario no contiene nada
	 * WARNING: si no hay lugar me quedo trabado
	 * @return Punto
	 */
	public static Punto ubicar() {
		Punto p1;
		do{
			p1 = new Punto ( (int)(Math.random()*(Medida.ANCHO/Medida.SIZE - 1)),
					(int)(Math.random()*(Medida.LARGO/Medida.SIZE - 1)) );
		}while(Escenario.matriz[p1.getX()][p1.getY()]!=0); //si no hay lugar me quedo trabado
		return p1;
	}

}
