/*
 * 
 */
package org.ranjith.account.plugin;

import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.ranjith.plugin.SavingsPlugin;
/**
 * 
 * @author XR1CTSO
 *
 */
public class AccountSavingsPlugin implements SavingsPlugin {
    private JPanel panel = new JPanel();
    
    JTextField bankNameTextField = new JTextField(30);
    JTextField accountNumberTextField = new JTextField(15);
    JTextField routingNumberTextField = new JTextField(10);
    
    public AccountSavingsPlugin(){
        initPanel();
    }
    private void initPanel() {
        JLabel bankNameLabel = new JLabel("Bank Name :");    
        JLabel accountNumberLabel = new JLabel("Account Number :");
        JLabel routingNumberLabel = new JLabel("Routing Number :");
        JButton saveButton = getSaveButton();
        JButton doneButton = getDoneButton();
        GroupLayout gl = new GroupLayout(panel);
        panel.setLayout(gl);
        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);
        gl.setHorizontalGroup(
                gl.createSequentialGroup().
                    addGroup(gl.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                     .addComponent(bankNameLabel)
                                                     .addComponent(accountNumberLabel)
                                                     .addComponent(routingNumberLabel)                                                   
                            ).
                    addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(bankNameTextField,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(accountNumberTextField,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(routingNumberTextField,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(
                                                               gl.createSequentialGroup()
                                                                 .addComponent(saveButton)
                                                                 .addGap(60)
                                                                 .addComponent(doneButton)
                                                               )                                                     

                            )
                            
              );
           
           gl.setVerticalGroup(
               gl.createSequentialGroup().addGroup(
                      gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                          .addComponent(bankNameLabel).addComponent(bankNameTextField)                                           
                      ).addGroup(
                      gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                          .addComponent(accountNumberLabel).addComponent(accountNumberTextField)
                      ).addGroup(
                      gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                          .addComponent(routingNumberLabel).addComponent(routingNumberTextField)
                      ).addGroup(
                      gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                          .addComponent(saveButton)
                          .addComponent(doneButton)
                      )

              );             
        
    }
    private JButton getDoneButton() {
        JButton button = new JButton("Done");
        return button;
    }
    private JButton getSaveButton() {
        JButton button = new JButton("Save");
        return button;    
    }
    
	public boolean delete(String key) {
		// TODO Auto-generated method stub
		return false;
	}
	public JComponent getAddUI() {
		// TODO Auto-generated method stub
		return panel;
	}
	public JComponent getEditUI(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	public double getSavingsAmount() {
		// TODO Auto-generated method stub
		return 0;
	}
	public String save(Map savingsKeyValue) {
		// TODO Auto-generated method stub
		return null;
	}
}
