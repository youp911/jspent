/**
 * 
 */
package org.ranjith.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * A utility class to use Hibernate session.
 * @author ranjith
 *
 */
public class HibernateUtil {
	public static final SessionFactory sessionFactory;
	String query = "From org.ranjith.jspent.data.Expense";
	static {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	public static void save(Object dataObject) {
		Session dbSession = sessionFactory.openSession();
		dbSession.beginTransaction();
		dbSession.save(dataObject);
		dbSession.getTransaction().commit();
		dbSession.close();
	}
}
