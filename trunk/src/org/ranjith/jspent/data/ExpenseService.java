/**
 * 
 */
package org.ranjith.jspent.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ranjith.util.HibernateUtil;


/**
 * A service to maninpulate expense data.
 * @author ranjith
 * 
 */
public class ExpenseService {
	public static final String[] EXPENSE_CATEGORIES = {"Mandatory","Flexible"};
    public static final String EXPENSE_CAT_MANDATORY = "Mandatory";
    public static final String EXPENSE_CAT_FLEXIBLE = "Flexible";
    public static final String EXPENSE_INCOMES = "Incomes";
    public static final String EXPENSE_EXPENSES = "Expenses";
 
    public ExpenseService() {
        
    }
    
    /**
     * Get list of expenses as <code>Expense</code> objects 
     * for a given month 
     * @param forMonth
     * @return list of expenses.
     */
    public List getExpenses(int forMonth) {
        Map paramMap = new HashMap(1);
        paramMap.put("month", forMonth);
        return HibernateUtil.getData("from org.ranjith.jspent.data.Expense where month(date)=:month ORDER BY id asc", paramMap);
    }
    
    /**
     * Return expense sub categories.
     * TODO: Configure through preferences
     * @return
     */
    public static String[] getExpenseSubCategories() {
    	return new String[] {"Grocery","Entertainment","Food & Drinks","Others"};
    }
    
    /**
     * Persist expense data
     * @param expenseData
     */
    public static void saveExpense(Expense expenseData) {
    	HibernateUtil.save(expenseData);
    }
    
    /**
     * Deletes the object from datastore
     * @param selectedRowObject
     */
    public static void delete(Expense expenseObject) {
        HibernateUtil.delete(expenseObject);
    }
    
    /**
     * Update expense data in datastore.
     * @param dataObject
     */
    public static void update(Expense dataObject) {
        HibernateUtil.update(dataObject);
    }
}
