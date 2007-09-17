/**
 * 
 */
package org.ranjith.plugin;

import java.util.Map;

import javax.swing.JComponent;

/**
 * @author XR1CTSO
 *
 */
public interface SavingsPlugin extends Plugin{
	
	/** 
	 * Returns UI component to display
	 * When adding a new Saving information.
	 * @return a UI form
	 */
	public JComponent getAddUI();
	
	/**
	 * Returns UI component to display
	 * when saving a modified Saving information.
	 * @param id Id of the savings info to load
	 * @return a UI form
	 */
	public JComponent getEditUI(String id);
	
	/**
	 * Adds the savings information to datastore,
	 * and returns the key assigned to the saving.
	 * @param savingsKeyValue
	 * @return savingsId
	 */
	public String save(Map savingsKeyValue);
	
	/**
	 * removes a savings information.
	 * @param key
	 * @return flag to indicate succesful removal.
	 */
	public boolean delete(String key);
	
	/**
	 * Gets amount associated with this savings.
	 * for example: In case of a bank account,
	 * this would be the balance.
	 * @return amount associated with the savings
	 */
	public double getSavingsAmount();
}
