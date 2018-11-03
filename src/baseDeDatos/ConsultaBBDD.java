package baseDeDatos;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ConsultaBBDD {
	//pasarle un objecto Persona
	public static boolean insert(Object obj) {
		
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");

		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();

		Transaction tx = session.beginTransaction();
		try {
			session.save(obj);
			System.out.println("Se genero el registro con éxito.....!!");
			
			tx.commit();
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
			factory.close();
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	//si no puedo hacer la consulta xq no encuentra el campu o otros motivos sea hace un .size a lista y si da 0 no hizo la consulta.
	//pasarle la consulta
	
	public static List<Object[]> consult(String consult){
		
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		List<Object[]> list_Of_Things = null;
		
		try {
			Query q = session.createQuery(consult);
			list_Of_Things = q.getResultList();
		}catch(HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
		return list_Of_Things;
	}
}
