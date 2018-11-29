package base;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Query;
import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import baseDeDatos.ConsultaBBDD;
import baseDeDatos.Persona;
import medida.Parametro;

public class DAOServidor extends DAO {

	Configuration cfg;
	SessionFactory factory;

	public DAOServidor() {
		cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");

		factory = cfg.buildSessionFactory();
	}

	@Override
	public List<Object[]> realizarLogin(Persona persona) {

		List<Object[]> list_Of_Things = null;
		Persona res = null;
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		String consulta = "select p.mail,p.contraseña,p.nick from Persona p ";
		consulta += "where p.mail=" + "'" + persona.getMail() + "'" + " and p.contraseña=" + "'"
				+ persona.getContraseña() + "'";

		try {
			Query q = session.createQuery(consulta);
			list_Of_Things = q.getResultList();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}

		return list_Of_Things;
	}
	
	@Override
	public int realizarRegistro(Persona persona) {

		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		Matcher verificador = pattern.matcher(persona.getMail());

		if (verificador.find() == false)
			return Parametro.CORREO_INVALIDO;
		else {
			if (persona.getMail().equals("") || persona.getContraseña().equals("") || persona.getNick().equals("")) {
				return Parametro.CAMPOS_VACIOS;
			} else {
				ConsultaBBDD.insert(persona);
				return Parametro.EXITO_REG;
			}
		}
	}

	
}
/* Manejo las consultas de datos con hibernate */
