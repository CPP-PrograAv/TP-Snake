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

		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		List<Object[]> list_Of_Things = null;

		String consulta = "select p.mail,p.contraseña,p.nick from Persona p ";
		consulta += "where p.mail=" + "'" + persona.getMail() + "'";

		Query q = session.createQuery(consulta);
		list_Of_Things = q.getResultList();

		if (list_Of_Things.size() == 1)
			return Parametro.DUPLICADO; // duplicado..
		else {
			try {
			session.save(persona);
			tx.commit();
			}
			catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			}
			return Parametro.EXITO_REG;
		}
	}
	
	

}
/* Manejo las consultas de datos con hibernate */
