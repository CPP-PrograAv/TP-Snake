package GameObjects;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

import base.*;
import medida.*;

public class Snake extends GameObject {
	private Tablero tablero;
	private static int ID = ++idgeneral;
	protected static int IDcuerpo = 99;// para darle un id a los cuerpos
	private int idSnake;
	public boolean muerto = false;
	private int direccion;
	private int puntaje;
	private ArrayList<Cuerpo> cuerpos = new ArrayList<Cuerpo>();
	private Color color;

	public Snake(int posX, int posY, int id, Color color, Tablero tablero) {
		super(new Punto(posX, posY), id, tablero);
		this.tablero = tablero;
		cuerpos.add(new Cuerpo(new Punto(posX - 1, posY), IDcuerpo, tablero));// 3, 4, 20, ID
		idSnake = id;
		direccion = 1;// set
		this.color = color;
	}

	public Snake(int posX, int posY, int id, int longitud, Tablero tablero) {
		super(new Punto(posX, posY), id, tablero);
		this.tablero = tablero;
		for (int i = 0; i < longitud; i++)
			cuerpos.add(new Cuerpo(new Punto(posX - i, posY), IDcuerpo,tablero));// 3, 4, 20, ID
		idSnake = id;

	}

	/**
	 * Mueve la serpiente una unidad en base a la direccion indicada
	 */
	public void move() {
		if (muerto || direccion == 0) // no habría que mover
			return;

		for (int i = cuerpos.size() - 1; i >= 0; i--) {

			if (i > 0)
				// a cada cuerpo le doy la posicion del anterior
				cuerpos.get(i).setPosition(cuerpos.get(i - 1).getPosX(), cuerpos.get(i - 1).getPosY(), ID);
			else
				// al primer cuerpo le doy la posicion de la cabeza
				cuerpos.get(i).setPosition(getPosX(), getPosY(), ID);

		}
		// muevo la cabeza y verifico los límites

		switch (direccion) {
		case Medida.NORTE:
			if (getPosY() - 1 < 0)
				setPosition(getPosX(), Medida.LARGO / Medida.SIZE - 1, ID);
			else
				setPosition(getPosX(), getPosY() - 1, ID);
			break;
		case Medida.SUR:
			if (getPosY() + 1 > Medida.LARGO / Medida.SIZE - 1)
				setPosition(getPosX(), 0, ID);
			else
				setPosition(getPosX(), getPosY() + 1, ID);
			break;
		case Medida.ESTE:
			if (getPosX() + 1 > Medida.ANCHO / Medida.SIZE - 1)
				setPosition(0, getPosY(), ID);
			else
				setPosition(getPosX() + 1, getPosY(), ID);
			break;
		case Medida.OESTE:
			if (getPosX() - 1 < 0)
				setPosition(Medida.ANCHO / Medida.SIZE - 1, getPosY(), ID);
			else
				setPosition(getPosX() - 1, getPosY(), ID);
			break;
		}
	}

	/**
	 * Añade un cuerpo al final
	 */
	public void crecer() {
		cuerpos.add(new Cuerpo(cuerpos.get(cuerpos.size() - 1).getPosition(), tablero));
		puntaje++;
	}

	public void morir() {

		muerto = true;
		new Fruta(new Punto(this.getPosX(), this.getPosY()), tablero);
		for (int i = 0; i < cuerpos.size(); i++) {
			new Fruta(new Punto(cuerpos.get(i).getPosX(), cuerpos.get(i).getPosY()), tablero);
		}

		cuerpos.clear();
	}

	/**
	 * Cambia la direccion de la serpiente, si la direccion indicada es contraria a
	 * la actual el cambio no se realiza
	 * 
	 * @param direccion
	 */
	public void setDireccion(int direccion) {
		// evito que se mueva para atras
		if (this.direccion == Medida.NORTE && direccion == Medida.SUR)
			return;
		if (this.direccion == Medida.SUR && direccion == Medida.NORTE)
			return;
		if (this.direccion == Medida.ESTE && direccion == Medida.OESTE)
			return;
		if (this.direccion == Medida.OESTE && direccion == Medida.ESTE)
			return;

		this.direccion = direccion;
	}

	/**
	 * @return tamaño del cuerpo de la serpiente
	 */
	public int getSizeSnake() {
		return this.cuerpos.size();
	}

	/**
	 * @return id de la serpiente
	 */
	public int getIdSnake() {
		return this.idSnake;
	}

	public ArrayList<Cuerpo> getCuerpos() {
		return this.cuerpos;
	}

	@Override
	public void paint(Graphics2D g2d) {
		g2d.setColor(this.color);
		int size = Medida.SIZE;
		int padding = Medida.BORDE / 2;
		g2d.fillRect(getPosX() * size + 1 + padding, getPosY() * size + 1 + padding, size - 2, size - 2);
		for (Cuerpo trozo : cuerpos)
			trozo.paint(g2d);
	}

	public boolean getMuerto() {
		return this.muerto;
	}

	public int getDireccion() {
		return direccion;
	}

	public int getPuntaje() {
		return this.puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	@Override
	public void accionColision(Snake snake) {

		if (Colision.colisionaCabeza(snake, this))
			this.morir();

		snake.morir();

	}

}
