package base;

import baseDeDatos.Persona;

public class DAOCliente extends DAO{
	/*Manejo las consultas de datos al servidor o la bd local con hibernate*/

	//Conexion c;
	public DAOCliente() {
		// this.c = conexion;
	}
	@Override
	public Persona realizarLogin(Persona p) {
		//c.logIn(p);
		p.setNick("Nickk");
		p.setIdPersona(1);
		p.setColor("Azul");
		return p;
	}
	
}
