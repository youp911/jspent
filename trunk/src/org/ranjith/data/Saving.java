/**
 * 
 */
package org.ranjith.data;

/**
 * @author XR1CTSO
 * A high-level saving amount
 */
public abstract class Saving implements java.io.Serializable {
    private double amount;
    private String notes;

    /**
     * Returns the amount available in this saving.
     * @return amount
     */
    public double getAmount() {
        return this.amount; 
    }
    
    /**
     * Sets the amount in this saving.
     * @param amount
     */
    public void setAmount(double amount){
        this.amount = amount;
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
}
