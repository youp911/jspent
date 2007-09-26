/**
 * 
 */
package org.ranjith.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.hibernate.Query;
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
	
	public static List getData(String ql,Map paramMap) {
	    List dataList  = new ArrayList();
	    Session dbSession = sessionFactory.openSession();
	    Query query = dbSession.createQuery(ql);
	    Set<Map.Entry<Object,Object>> entrySet = paramMap.entrySet();
	    for(Map.Entry entry : entrySet){
	        query.setParameter((String)entry.getKey(),entry.getValue());
	    }
	    dataList = query.list();
	    dbSession.close();
	    return dataList;
	}
	
    public static void delete(Object dataObject) {
        Session dbSession = sessionFactory.openSession();
        dbSession.beginTransaction();
        dbSession.delete(dataObject);
        dbSession.getTransaction().commit();
        dbSession.close();
    }
	
}
