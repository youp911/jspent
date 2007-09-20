/**
 * 
 */
package org.ranjith.data;

import java.util.Date;

/**
 * @author ranjith
 *
 */
public class Expense {
	private int id;
	private String category;
	private String subCategory;
	private Date date;
	private float amount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }
    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
