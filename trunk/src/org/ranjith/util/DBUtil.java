/**
 * 
 */
package org.ranjith.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author XR1CTSO
 *
 */
public class DBUtil {

    public static void createSavingsDBTables() {
        try{
            Connection con = DriverManager.getConnection("jdbc:derby:jSpentDB;create=true");
            Statement stmt = con.createStatement();
            stmt.execute("CREATE TABLE SAVINGS (ID INT PRIMARY KEY, AMOUNT DOUBLE, PLUGINNAME VARCHAR(256)");
            stmt.execute("CREATE TABLE SAVINGSDATA (DATAKEY VARCHAR(50),DATAVALUE VARCHAR(50),SAVINGSID INT,CONSTRAINT SAVINGS_FK" +
        		" FOREIGN KEY (SAVINGSID) REFERENCES SAVINGS (ID)");   
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        DBUtil.createSavingsDBTables(); 
    }
}
