/**
 * 
 */
package org.ranjith;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author XR1CTSO
 * 
 */
public class ExpenseService {
    public static final String EXPENSE_MANDATORY = "Mandatory";
    public static final String EXPENSE_FLEXIBLE = "Flexible";
    public static final String EXPENSE_INCOMES = "Incomes";
    public static final String EXPENSE_EXPENSES = "Expenses";
    public ExpenseService() {
        
    }
    public List getExpenses() {
        List list = new ArrayList();
        for (int i = 0; i < 55; i++) {
            Expense expense = new Expense();
            expense.setId(i);
            expense.setCategory("Cat-5");
            expense.setDate(new Date());
            expense.setSubCategory("Subcat" + i);
            expense.setAmount(100f * i);
            list.add(expense);
        }
        return list;
    }
}
