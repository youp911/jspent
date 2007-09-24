/**
 * 
 */
package org.ranjith.jspent.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public List getExpenses() {
        List list = new ArrayList();
        for (int i = 0; i < 55; i++) {
            Expense expense = new Expense();
            expense.setId(i);
            expense.setCategory(EXPENSE_CATEGORIES[1]);
            expense.setDate(new Date());
            expense.setSubCategory("Subcat" + i);
            expense.setAmount(100f * i);
            expense.setNotes("spent this money thoughtlessly.But still think it was mandatory");
            list.add(expense);
        }
        return list;
    }
    
    public static String[] getExpenseSubCategories() {
    	return new String[] {"Grocery","Entertainment","Food & Drinks","Others"};
    }
    
    public static void saveExpense(Expense expenseData) {
    	HibernateUtil.save(expenseData);
    }
}
