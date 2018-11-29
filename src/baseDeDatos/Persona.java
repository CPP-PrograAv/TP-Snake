package baseDeDatos;

import java.io.Serializable;

public class Persona implements Serializable{
	private int idPersona;
	private String nick;
	private String mail;
	private String contraseña;
	private String color;
	
	public Persona(int idPersona, String nick, String color) {
		this.idPersona = idPersona;
		this.nick = nick;
		this.color = color;
	}
	
	public Persona() {
	}
	
	public Persona(int idPersona, String nick, String mail, String contraseña, String color) {
		this.idPersona = idPersona;
		this.nick = nick;
		this.mail = mail;
		this.contraseña = contraseña;
		this.color = color;
	}

	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "Persona [idPersona=" + idPersona + ", nick=" + nick + ", mail=" + mail + ", contraseña=" + contraseña
				+ ", color=" + color + "]";
	}
	
	
}
