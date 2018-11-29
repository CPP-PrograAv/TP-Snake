package cliente;

import java.io.Serializable;

public class Mensaje implements Serializable {

	private int cod;
	private Object dato;
	
	public Mensaje(int cod, Object dato) {
		
		this.cod = cod;
		this.dato = dato;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public Object getDato() {
		return dato;
	}

	public void setDato(Object dato) {
		this.dato = dato;
	}
	
	
}
