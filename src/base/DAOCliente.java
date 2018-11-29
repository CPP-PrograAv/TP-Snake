package base;

import java.util.List;

import baseDeDatos.Persona;

public class DAOCliente extends DAO{

	
	
	public DAOCliente() {
		// this.c = conexion;
	}
	@Override
	public List<Object[]> realizarLogin(Persona p) {
		//c.logIn(p);
		p.setNick("Nickk");
		p.setIdPersona(1);
		p.setColor("Azul");
		return (List<Object[]>) p;
	}
	
}
