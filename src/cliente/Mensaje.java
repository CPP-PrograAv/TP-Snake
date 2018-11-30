package cliente;

import java.io.Serializable;

public class Mensaje implements Serializable {

	private int cod;
	private Object dato;
	private String cadena;
	private int indice;
	private String tipoJuego;
	private String tipoModoFruta;
	
	
	public Mensaje(int cod, Object dato,int indice) {
		this.cod = cod;
		this.dato = dato;
		this.indice=indice;
	}
	
	public Mensaje(int cod) {
		this.cod = cod;
	}

	public Mensaje(int cod, Object dato) {

		this.cod = cod;
		this.dato = dato;
	}

	public Mensaje(int cod, Object dato, String cadena) {

		this.cod = cod;
		this.dato = dato;
		this.cadena = cadena;
	}
	
	public Mensaje(int cod, Object dato, String cadena, String tipoJuego, String cantFruta) {

		this.cod = cod;
		this.dato = dato;
		this.cadena = cadena;
		
	}


	public String getTipoJuego() {
		return tipoJuego;
	}

	public void setTipoJuego(String tipoJuego) {
		this.tipoJuego = tipoJuego;
	}

	public String getTipoModoFruta() {
		return tipoModoFruta;
	}

	public void setTipoModoFruta(String tipoModoFruta) {
		this.tipoModoFruta = tipoModoFruta;
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

	public String getCadena() {
		return cadena;
	}

	public void setCadena(String cadena) {
		this.cadena = cadena;
	}
	public int getIndice() {
		return indice;
	}
	public void setIndice(int indice) {
		this.indice = indice;
	}

}
