package baseDeDatos;

public class Persona {
	
	private int idPersona;
	private String nick;
	private String mail;
	private String contraseña;
	private String color;

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
