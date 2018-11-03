package GameObjects;

import java.awt.*;
import base.*;
import medida.Medida;

public abstract class GameObject {

	private Punto ubicacion;
	private int size;
	protected static int idgeneral = 0;

	public GameObject(int s) {
		this.ubicacion.setPunto(1, 1);
		this.size = s;
	}

	public GameObject(Punto ubicacion, int ID) {
		this.ubicacion = ubicacion;
		Tablero.matriz[ubicacion.getX()][ubicacion.getY()] = ID;
		Tablero.tablero[ubicacion.getX()][ubicacion.getY()] = this;
	}

	public void paint(Graphics2D g2d) {
		int padding = Medida.BORDE / 2;
		g2d.fillRect(getPosX() * size + 1 + padding, getPosY() * size + 1 + padding, size - 2, size - 2);
	}

	public Punto getPosition() {
		return this.ubicacion;
	}

	public void setPosition(int posX, int posY, int ID) {
		if (Tablero.tablero[getPosX()][getPosY()] == this)
			Tablero.tablero[getPosX()][getPosY()] = null;
		this.ubicacion.setX(posX);
		this.ubicacion.setY(posY);
		Tablero.matriz[posX][posY] = ID;
		Tablero.tablero[posX][posY] = this;
	}

	public void setPosition(Punto p, int ID) {
		if (Tablero.tablero[getPosX()][getPosY()] == this)
			Tablero.tablero[getPosX()][getPosY()] = null;
		this.ubicacion.setX(p.getX());
		this.ubicacion.setY(p.getY());
		Tablero.matriz[p.getX()][p.getY()] = ID;
		Tablero.tablero[p.getX()][p.getY()] = this;
	}

	public int getPosX() {
		return this.ubicacion.getX();
	}

	public void setPosX(int posX) {
		this.ubicacion.setX(posX);
		;
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

	@Override
	public String toString() {
		return "GameObject [ubicacion=" + ubicacion + ", size=" + size + "]";
	}

}
