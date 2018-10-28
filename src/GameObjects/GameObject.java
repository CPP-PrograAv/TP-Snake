package GameObjects;

import java.awt.*;
import javax.swing.*;
import base.*;

public abstract class GameObject{

	private Punto ubicacion;
	private int size;
	protected static int idgeneral = 0;
	
	public GameObject(int s) {
		this.ubicacion.setPunto(1, 1);
		this.size=s;
	}
	
	public GameObject(Punto ubicacion, int ID) {
		this.ubicacion = ubicacion;
		Escenario.matriz[ubicacion.getX()][ubicacion.getY()] = ID;
	}
	
	public abstract void paint(Graphics2D g2d);
	
	public Punto getPosition() {
		return this.ubicacion;
	}
	
	public String toString() {
		return (this.ubicacion.getX() + " " + this.ubicacion.getY());
	}
	
	public void setPosition(int posX, int posY, int ID) {
		this.ubicacion.setX(posX);
		this.ubicacion.setY(posY);
		Escenario.matriz[posX][posY] = ID;
	}
	
	public void setPosition(Punto p, int ID) {
		this.ubicacion.setX(p.getX());
		this.ubicacion.setY(p.getY());
		Escenario.matriz[p.getX()][p.getY()] = ID;
	}

	public int getPosX() {
		return this.ubicacion.getX();
	}

	public void setPosX(int posX) {
		this.ubicacion.setX(posX);;
	}

	public int getPosY() {
		return this.ubicacion.getY();
	}

	public void setPosY(int posY) {
		this.ubicacion.setY(posY);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ubicacion == null) ? 0 : ubicacion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameObject other = (GameObject) obj;
		if (ubicacion == null) {
			if (other.ubicacion != null)
				return false;
		} else if (!ubicacion.equals(other.ubicacion))
			return false;
		return true;
	}
	
}
