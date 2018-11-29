package base;

import java.util.List;

import baseDeDatos.Persona;

public abstract class DAO {
	public abstract List<Object[]> realizarLogin(Persona p); 
	
	public abstract int realizarRegistro(Persona persona);

}
