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
 * @author XR1CTSO
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
    public List getExpenses(int forMonth) {
        Map paramMap = new HashMap(1);
        paramMap.put("month", forMonth);
        return HibernateUtil.getData("from org.ranjith.jspent.data.Expense where month(date)=:month ORDER BY id asc", paramMap);
    }
    
    public static String[] getExpenseSubCategories() {
    	return new String[] {"Grocery","Entertainment","Food & Drinks","Others"};
    }
    
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
    public static void update(Expense dataObject) {
        HibernateUtil.update(dataObject);
    }
}
