package base;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import baseDeDatos.Persona;

public class DAOServidor extends DAO {

	Configuration cfg;
	SessionFactory factory;

	public DAOServidor() {
		cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");

		factory = cfg.buildSessionFactory();
	}
	
	@Override
	public Persona realizarLogin(Persona p) {
		List<Persona> list_Of_Things = null;
		Persona res = null;
		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			Query q = session.createQuery("SELECT p.mail,p.contraseña,p.nick FROM Persona p \n"
				+ " WHERE p.mail='" + p.getMail() + "'\n" +
					" AND p.contraseña='" + p.getContraseña() + "'");
			list_Of_Things = q.getResultList();
		}catch(HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		if(list_Of_Things.size()==1)
			return list_Of_Things.get(0);
		return res;
	}
	/*Manejo las consultas de datos con hibernate*/
	
}
