package GameObjects;

import java.awt.*;
import java.util.ArrayList;

import base.*;
import medida.*;

public class Snake extends GameObject{

	private static int ID = ++idgeneral;
	protected static int IDcuerpo = 99;//para darle un id a los cuerpos
	//private static int IDcabeza = 1;
	private int idSnake;
	public boolean muerto= false;
	boolean comer=false;
	private int direccion;
	
	private ArrayList<Cuerpo> cuerpos = new ArrayList<Cuerpo>();
	
	public Snake(int posX, int posY, int id) {
		super(new Punto(posX,posY),id);
		cuerpos.add( new Cuerpo( new Punto(posX-1,posY),IDcuerpo));//3, 4, 20, ID
		idSnake = id;
		direccion = 0;
		
	}
	
	public Snake(int posX, int posY, int id, int longitud) {
		super(new Punto(posX,posY),id);
		for(int i = 0;i<longitud;i++)
			cuerpos.add( new Cuerpo( new Punto(posX-i,posY),IDcuerpo));//3, 4, 20, ID
		idSnake = id;
		
	}

	@Override
	public void paint(Graphics2D g2d) {
		
		int size = Medida.SIZE;
		int padding = Medida.BORDE/2;
		g2d.fillRect(getPosX()*size + 1 + padding, getPosY()*size + 1 + padding , size - 2, size - 2);
		for(Cuerpo trozo :cuerpos)
			trozo.paint(g2d);
	}


	public void move(int dx, int dy) {
	
		
		for (int i = cuerpos.size() - 1; i >=0; i--) { 
	
//			if(i == cuerpos.size()-1 && !comer) 
//				Escenario.matriz[cuerpos.get(i).getPosX()]
//								[cuerpos.get(i).getPosY()] = 0;
			if(i>0) 
				cuerpos.get(i).setPosition(cuerpos.get(i - 1).getPosX(), cuerpos.get(i - 1).getPosY(), ID);	
			else
				cuerpos.get(i).setPosition(getPosX(), getPosY(), ID);
			comer=false;
		}

		setPosition(getPosX()+dx, getPosY()+dy, ID);//creo que mueve la cabeza
	}
	
	public void move() {
		for (int i = cuerpos.size() - 1; i >=0; i--) { 
			
			if(i == cuerpos.size()-1 && !comer) //Que significa? 
				Escenario.matriz[cuerpos.get(i).getPosX()]
								[cuerpos.get(i).getPosY()] = 0;
			if(i>0) 
				cuerpos.get(i).setPosition(cuerpos.get(i - 1).getPosX(), cuerpos.get(i - 1).getPosY(), ID);	
			else
				cuerpos.get(i).setPosition(getPosX(), getPosY(), ID);
			comer=false;
		}

//		setPosition(getPosX()+dx, getPosY()+dy, ID);//creo que mueve la cabeza
	}


	public void crecer() {
		cuerpos.add( new Cuerpo(cuerpos.get(cuerpos.size()-1).getPosition()) ); 	// hago que el cuerpo aparesca afuera de la ventana,
												// pero al agregarlo a la lista
		comer=true;	
	}
	

	public int getSizeSnake() {
		return this.cuerpos.size();
	}
	
	public int getIdSnake() {
		return this.idSnake;
	}
	
	public ArrayList<Cuerpo> getCuerpos(){
		return this.cuerpos;
	}
	

}
