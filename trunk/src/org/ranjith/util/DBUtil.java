/**
 * 
 */
package org.ranjith.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Database utility class.
 * Will add an abstraction layer above database.
 * @author ranjith
 *
 */
public class DBUtil {

    public static void createSavingsDBTables() {
    	Connection con = null;
    	Statement stmt = null;
    	try{
            con = DriverManager.getConnection("jdbc:derby:jSpentDB;create=true;user=dbuser;password=dbuserpwd");
            stmt = con.createStatement();
//            stmt.execute("CREATE TABLE SAVINGS (ID INT PRIMARY KEY, AMOUNT DOUBLE, PLUGINNAME VARCHAR(256)");
//            stmt.execute("CREATE TABLE SAVINGSDATA (DATAKEY VARCHAR(50),DATAVALUE VARCHAR(50),SAVINGSID INT,CONSTRAINT SAVINGS_FK" +
//        		" FOREIGN KEY (SAVINGSID) REFERENCES SAVINGS (ID)"); 
            stmt.execute(getCreateExpenseTablesSQL());
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
        	try {
        	 stmt.close();
        	 con.close();
        	}catch(Exception e) {}
        }
    }
    
    public static void main(String[] args) {
        DBUtil.createSavingsDBTables(); 
    }
    
    
    private static String getCreateExpenseTablesSQL() {
    	return "CREATE table EXPENSES ( " +
    		   "ID          INTEGER NOT NULL " +  
               "PRIMARY KEY GENERATED ALWAYS AS IDENTITY " +
               "(START WITH 1, INCREMENT BY 1), " +
                "CATEGORY    VARCHAR(30), " +
                "SUBCATEGORY VARCHAR(30), " +
                "EXPENSEDATE DATE, " +
                "AMOUNT      FLOAT, " +
                "NOTES       VARCHAR(30))";

    }
}
