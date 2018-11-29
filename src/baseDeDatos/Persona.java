package baseDeDatos;

import java.io.Serializable;

public class Persona implements Serializable{
	private int idPersona;
	private String nick;
	private String mail;
	private String contrase�a;
	private String color;
	
	public Persona(int idPersona, String nick, String color) {
		this.idPersona = idPersona;
		this.nick = nick;
		this.color = color;
	}
	
	public Persona() {
	}
	
	public Persona(int idPersona, String nick, String mail, String contrase�a, String color) {
		this.idPersona = idPersona;
		this.nick = nick;
		this.mail = mail;
		this.contrase�a = contrase�a;
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
	public String getContrase�a() {
		return contrase�a;
	}
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "Persona [idPersona=" + idPersona + ", nick=" + nick + ", mail=" + mail + ", contrase�a=" + contrase�a
				+ ", color=" + color + "]";
	}
	
	
}
