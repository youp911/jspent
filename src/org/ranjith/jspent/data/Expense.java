/**
 * 
 */
package org.ranjith.jspent.data;

import java.util.Date;

import org.ranjith.util.DataTypeUtil;

/**
 * Basic expense data object
 * @author ranjith
 *
 */
public class Expense {
	private int id;
	private String category;
	private String subCategory;
	private Date date;
	private float amount;
	private String notes;
	
	/**
	 * Get's expense's unique id.
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Set id to the expense.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Get expense category
	 * @see ExpenseService for constants.
	 * @return
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * Set category for the expense.
     * @see ExpenseService for constants.
	 * @param category
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	/**
	 * Get sub category for the expense.
	 * @return
	 */
	public String getSubCategory() {
		return subCategory;
	}
	
	/**
     * Set sub category for the expense. 
	 * @param subCategory
	 */
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	
	/**
	 * Get amount for the expense.
	 * @return amount
	 */
	public float getAmount() {
		return amount;
	}
	
	/**
	 * Set amount for the expense.
	 * @param amount
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}
    /**
     * Expense year, month and date
     * no time values returned.
     * Any time related value will be 0
     * @return the date
     */
    public Date getDate() {
        return DataTypeUtil.getDateYYYYMMDD(date);
    }
    /**
     * Date to be set with year,month,and date
     * Values for Time are disregarded.
     * @param date the date to set
     */
    public void setDate(Date date) {
    	if(date == null) {
    		throw new IllegalArgumentException("Empt date value!");
    	}
    	this.date = DataTypeUtil.getDateYYYYMMDD(date);
    }
    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }
    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(amount);
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result
				+ ((subCategory == null) ? 0 : subCategory.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Expense))
			return false;
		final Expense other = (Expense) obj;
		if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (subCategory == null) {
			if (other.subCategory != null)
				return false;
		} else if (!subCategory.equals(other.subCategory))
			return false;
		return true;
	}
    
}
