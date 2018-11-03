package GameObjects;

import java.awt.*;
import java.util.ArrayList;

import base.*;
import medida.*;

public class Snake extends GameObject {

	private static int ID = ++idgeneral;
	protected static int IDcuerpo = 99;// para darle un id a los cuerpos
	private int idSnake;
	public boolean muerto = false;
	boolean comer = false;
	private int direccion;
	private int puntaje;

	private ArrayList<Cuerpo> cuerpos = new ArrayList<Cuerpo>();

	/**
	 * Crea una snake en la posicion x, y con id indicados
	 * 
	 * @param poición  X
	 * @param posición Y
	 * @param id
	 */
	public Snake(int posX, int posY, int id) {
		super(new Punto(posX, posY), id);
		cuerpos.add(new Cuerpo(new Punto(posX - 1, posY), IDcuerpo));// 3, 4, 20, ID
		idSnake = id;
		direccion = 1;// set
	}

	/**
	 * Crea una snake en la posicion X,Y,de id y longitud indicados
	 * 
	 * @param poición  X
	 * @param posición Y
	 * @param id
	 * @param longitud
	 */
	public Snake(int posX, int posY, int id, int longitud) {
		super(new Punto(posX, posY), id);
		for (int i = 0; i < longitud; i++)
			cuerpos.add(new Cuerpo(new Punto(posX - i, posY), IDcuerpo));// 3, 4, 20, ID
		idSnake = id;

	}

	/**
	 * Mueve a la serpiente la cantidad indicada en los ejes dx y dy valores entre
	 * 0,1 y -1
	 * 
	 * @deprecated
	 * @param distancia X
	 * @param distancia Y
	 */
	public void move(int dx, int dy) {
		// aca habría que chequear los límites?*si se quisieran implementar
		// muevo los cuerpos
		for (int i = cuerpos.size() - 1; i >= 0; i--) {
//			if(i == cuerpos.size()-1 && !comer) //reacomodo el ultimo cuerpo (se creaba fuera de la pantalla)
//				Escenario.matriz[cuerpos.get(i).getPosX()]
//								[cuerpos.get(i).getPosY()] = 0;
			if (i > 0)
				// a cada cuerpo le doy la posicion del anterior
				cuerpos.get(i).setPosition(cuerpos.get(i - 1).getPosX(), cuerpos.get(i - 1).getPosY(), ID);
			else
				// al primer cuerpo le doy la posicion de la cabeza
				cuerpos.get(i).setPosition(getPosX(), getPosY(), ID);
			comer = false;
		}

		setPosition(getPosX() + dx, getPosY() + dy, ID);// muevo la cabeza
	}

	/**
	 * Mueve la serpiente una unidad en base a la direccion indicada
	 */
	public void move() {
		if (muerto || direccion == 0) // no habría que mover
			return;

		for (int i = cuerpos.size() - 1; i >= 0; i--) {

//			if(i == cuerpos.size()-1 && !comer) //reacomodo el ultimo cuerpo (se creaba fuera de la pantalla)
//			Escenario.matriz[cuerpos.get(i).getPosX()]
//							[cuerpos.get(i).getPosY()] = 0;
			if (i > 0)
				// a cada cuerpo le doy la posicion del anterior
				cuerpos.get(i).setPosition(cuerpos.get(i - 1).getPosX(), cuerpos.get(i - 1).getPosY(), ID);
			else
				// al primer cuerpo le doy la posicion de la cabeza
				cuerpos.get(i).setPosition(getPosX(), getPosY(), ID);
			comer = false;
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
		cuerpos.add(new Cuerpo(cuerpos.get(cuerpos.size() - 1).getPosition())); // Agrego el cuerpo A LA IZQUIERDA de la
																				// posicion del último

		comer = true;
		puntaje++;
	}

	/**
	 * Mata a la snake
	 */
	public void morir() {
		muerto = true;
		// dejo al escenario que limpie?
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

		int size = Medida.SIZE;
		int padding = Medida.BORDE / 2;
		g2d.fillRect(getPosX() * size + 1 + padding, getPosY() * size + 1 + padding, size - 2, size - 2);
		for (Cuerpo trozo : cuerpos)
			trozo.paint(g2d);
	}

	public int getDireccion() {
		return direccion;
	}
	public int getPuntaje() {
		return this.puntaje;
	}
}

